package Utils;

/**
 * Implementa uma string com template, permitindo alterar apenas os valores
 * desejados
 * @author Victor Capone
 */
public class TemplateString {

    /**
     * Define o template da string, cada posição que será alterada deverá ser
     * marcada com <code>$</code>
     * e.g.:"Hello $!"
     */
    protected String template;
    protected String[] params;
    protected final Character PARAM_TOKEN = '$';

    public TemplateString(String template) {
        setTemplate(template);
    }
    
    /**
     * Definida a n-ésima ocorrência de <code>$</code> para o paramêtro Especificado
     * @param n índice '0-based' do parametro a ser definido
     * @param value Valor do paramêtro
     * @throws IndexOutOfBoundsException caso não exista o paramêtro do indice 
     * especificado
     */
    public synchronized void setParam(int n, String value) 
            throws IndexOutOfBoundsException{
        params[n] = value;
    }
    
    /**
     * Define um novo template para a string, apaga todos os parametro 
     * já definidos
     * @param template o novo template
     */
    public synchronized final void setTemplate(String template){
        this.template = template;
        this.template = template;
        int count = 0;
        for (char c : template.toCharArray()) {
            count += c==PARAM_TOKEN?1:0;
        }
        params = new String[count];
    }
    
    /**
     * Retorna o template da String
     * @return O template bruto, especificado no construtor ou definido pelo 
     * método <code>setTemplate()</code>
     */
    public synchronized String getTemplate(){
        return template;
    }
    
    @Override
    public synchronized String toString(){
        String r = template;
        for(String p : params){
            r = r.replaceFirst("["+PARAM_TOKEN+"]", p);
        }
        return r;
    }
}
