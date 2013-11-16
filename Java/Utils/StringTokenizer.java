package Utils;

/**
 * Implementa um Stirng tokenizer para dividir uma cadeia de caracteres ao redor
 * de delimitadores especificos
 * @author Victor Capone
 */
public class StringTokenizer {
    protected String str, delims;
    protected boolean returnDelims=true;
    protected int offset;

    public StringTokenizer(String d) {
        delims = d;
        offset = 0;
    }

     public StringTokenizer(String d, boolean returnDelims) {
        this("", d, returnDelims);
    }
    
    public StringTokenizer(String str, String delims) {
        this(delims);
        this.str = str;
    }

    public StringTokenizer(String str, String delims, boolean returnDelims) {
        this(str, delims);
        this.returnDelims = returnDelims;
    }

    /**
     * @return Retorna o próximo token da string, se o token for um delimitador
     * só o retornara se <code>returnDelims==true.</code>
     */
    public String nextToken() {
        String r = "", token;
        while (hasMoreTokens()) {
            token = str.charAt(offset)+"";
            if (!delims.contains(token)) {
                r += str.charAt(offset++);
            }else{
                //Se o token for um delimitador
                if (r.length() > 0) {
                    return r;
                }else if (returnDelims) {
                    /*Ainda não foram lidos outros dados e delimitadores devem
                    retornados */
                    offset++;
                    return token;
                }else{
                    /*Nenhum dado lido e delimitadors não devem ser retornados
                     pula o delimitador e continua lendo*/
                    offset++;
                }
            }
        }
        return r;
    }
    
    /**
     * @param delims Delimitadores a considerar ao invés dos padroes
     * @return O próximo token considerando os delimitadores passados
     * como parametro
     */
    public String nextToken(String delims){
        String oldDelims = this.delims;
        this.delims = delims;
        String t = nextToken();
        this.delims = oldDelims;
        return t;
    }
    
    /**
     * @param delims Delimitadores a considerar, além dos padrões
     * @return O próximo token considerando os delimitadores passados
     * como parametro e os determinado no construtor
     */
    public String nextTokenAppend(String delims){
        String oldDelims = this.delims;
        this.delims = delims + this.delims;
        String t = nextToken();
        this.delims = oldDelims;
        return t;
    }
    
   /**
    * Permite visualizar o próximo token sem mover o offset da String
    * @return O próximo token que será lido pelo método <code>nextToken()</code>
    */
    public String previewNextToken(){
        int offsetAntes = offset;
        String token = this.nextToken();
        offset = offsetAntes;
        return token;
    }
    /**
     * @return <code>True</code> se ainda há tokens para ler, 
     * <code>false</code> se não
     */
    public boolean hasMoreTokens() {
        return offset < str.length();
    }

   /**
    * Redefine a tring e reseta os offsets
    * @param str 
    */
     public void setStr(String str) {
        this.str = str;
        offset = 0;
    }

    public boolean doReturnDelims() {
        return returnDelims;
    }

    public void setReturnDelims(boolean returnDelims) {
        this.returnDelims = returnDelims;
    }
    
     
}
