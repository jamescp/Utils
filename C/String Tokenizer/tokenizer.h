#ifndef TOKENIZER
#define TOKENIZER

typedef struct tok_str{
    int idx;
    int len;
    int returnDelims;
    char* delims;
    char* str;
}tok_str;

/*Devolve um ponteiro do tipo tok_str, o primeiro parametro é a string, o segundo os delimitadores
e o terceiro se os delimitadores devem ser retornados por nextToken ou não */
tok_str* newTokenizer(char*, char*, int);

/*Devolve o próximo token de um objeto do tipo tok_str */
char* nextToken(tok_str*);

/*Devolve um integer maior que 0 se existem mais tokens em um tok_str* */
int hasMoreTokens(tok_str* str);
#endif
