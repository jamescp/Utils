package FileReader.ReadingStrategy;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Victor Capone
 */
public class ObjectReadingStrategy<E extends Serializable> 
    extends FileReadingStrategy {

    protected ObjectInputStream ois;

    public ObjectReadingStrategy(File f) throws IOException {
        super(f);
        ois = new ObjectInputStream(new FileInputStream(f));
    }

    /**
     * Le um objeto do arquivo
     *
     * @return O Objeto Lido
     * @throws <code>RuntimeException</code> se não foi possível ler o objeto
     */
    @Override
    public synchronized E readNext() {
        try {
            return (E) ois.readObject();
        } catch (IOException ex) {
            throw new RuntimeException("Erro de E/S: " + ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Classe não encontrada: " + ex.getMessage(), ex);
        }
    }

    /**
     * Le todos os objetos do arquivo
     * @return Um vetor com todos os objetos lidos, caso ocorra algum erro
     * retorna até o ultimo objeto lido com sucesso
     */
    @Override
    public synchronized E[] readFull() {
        Object[] result = new Object[100];
        int idx = 0;
        while (hasNext()) {
            try {
                result[idx++] = readNext();
            }catch(RuntimeException e){
                System.err.println("Ocorreu um erro na leitura do arquivo");
                System.err.println(e.getMessage());
                break;
            }
            if(idx >= result.length){
                //Dobra o tamanho do vetor se necessário
                result = Arrays.copyOf(result, idx*2);
            }

        }

        return Arrays.copyOf((E[])result, idx);

    }

    @Override
    public synchronized boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
