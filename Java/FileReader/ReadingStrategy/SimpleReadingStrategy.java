package FileReader.ReadingStrategy;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author VictorCapone
 */
public class SimpleReadingStrategy extends FileReadingStrategy{
    protected Scanner reader;

    public SimpleReadingStrategy(File f) throws FileNotFoundException {
        super(f);
        this.reader =  new Scanner(new FileInputStream(this.file));
    }

    @Override
    public synchronized Object readNext() {
        return reader.nextLine();
    }

    @Override
    public synchronized Object[] readFull() {
        String[] read = new String[100];
        int qtos = 0;
        while (reader.hasNext()){
            read[qtos++] = reader.nextLine();
            if(qtos == read.length){
                //Se o tamanho limite for atingido, dobra o vetor de linhas
                read = Arrays.copyOf(read, qtos*2);
            }
        }
        //Retorna o vetor truncado
        return Arrays.copyOf(read, qtos);
    }

    @Override
    public synchronized boolean hasNext() {
        return reader.hasNext();
    }
    
}
