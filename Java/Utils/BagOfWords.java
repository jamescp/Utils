package Utils;

import estruturas.HashTable;
import hashing.SimpleHashingStrategy;

/**
 * Implementa uma Bag of Words[Thread Safe]
 * @author Victor Capone
 * @version 1.0T
 */
public class BagOfWords {

    private static final String DEFAULT_DELIMS = "[^\\w-]+";
    protected String delimiters;
    protected HashTable<String, Integer> frequencies;
    protected int nWords;

    
    /**
     * Construtor padrão
     */
    public BagOfWords() {
        frequencies = new HashTable<>(new SimpleHashingStrategy());
        nWords = 0;
        delimiters = DEFAULT_DELIMS;
    }
    
    /**
     * Construtor padrão
     * @param regex a regex que indica os delimitadores dessa Bag of Words
     * a regex padrão é <code>[^\\w-]+</code>
     */
    public BagOfWords(String regex) {
        this();
        delimiters = regex;
    }

    /**
     * Retorna um dicionário contendo cada palavra da string mapeada para o
     * número de ocorrencias da mesma dentro da string
     * @param s a string a ser mapeada
     * @return HashTable com as chaves sendo as palavras da String
     */
    public synchronized static HashTable<String, Integer> 
            parseQuantity(String s) {
        return parseToDic(s, new HashTable(new SimpleHashingStrategy()));
    }
    
    /**
     * Adiciona as palavras de uma string a uma Hashtable
     * @param s A string a ser processada
     * @param h A HashTable que servirá de dicionário
     * @return <code>h</code> com as ocorrências em <code>s</code> mapeadas para
     * Strings  usando a regex padrão <code>"[^\\w-]+"</code>
     */
    public static synchronized HashTable<String,Integer> parseToDic(String s, 
            HashTable<String, Integer> h){
        String[] words = s.split(DEFAULT_DELIMS);
        for (String w : words) {
            Integer v = h.getValue(w);
            if (v == null) {
                h.set(w, 1);
            } else {
                h.set(w, v + 1);
            }
        }
        return h;
    }
    /**
     * Adiciona novas palavras a essa bag of Words e as mapeia
     * @param s String contendo as palavras a serem adicionadas
     * @return <code>true</code> sempre
     */
    public synchronized boolean addToBag(String s){
        parseToDic(s, this.frequencies);
        nWords += s.split(delimiters).length;
        return true;
    }
    
    /**
     * Retorna o numero de vezes que uma dada palavra aparece nessa bag of words
     * @param w a palavra a ser consultada
     * @return Numero de ocorrências de <code>w</code>, case-insensitive
     */
    public synchronized int getNumberOf(String w){
        Integer i = frequencies.getValue(w);
        return i != null? i : 0;
    }
    
    /**
     * Retorna a frequencia da palavra dentro da String a frequencia é dada por
     * <code>getNumberOf(w)/numberOfWords()</code>
     * @param w a palavra que se deseja saber a frequência
     * @return a frequência da palavra <code>w</code> nessa Bag of Words
     */
    public synchronized double getFrequencyOf(String w){
        return nWords!=0? ((double)getNumberOf(w)/nWords):0;
    }
    
    /**
     * @return O número de palavras da string desconsiderando pontuação
     * Ou seja <code>∑getNumberOf(w)</code> para toda palavra <code>w</code> 
     * pertencente a essa Bag of Words
     */
    public synchronized int numberOfWords(){
        return nWords;
    }
}
