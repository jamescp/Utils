#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "tokenizer.h"


tok_str* newTokenStr(char* str, char* delims, int returnDelims){
    tok_str* r = (tok_str*)malloc(sizeof(tok_str));
    r->delims = delims;
    r->str = str;
    r->len = strlen(str);
    r->returnDelims = returnDelims;
    r->idx=0;
    return r;
}

char* nextToken(tok_str* str){
    char* r = calloc(str->len, sizeof(char));
    int idxR=0;
    while(hasMoreTokens(str)){
        if(strchr(str->delims, str->str[str->idx]) == NULL){
            r[idxR++] = str->str[str->idx++];

        }else{
            if(strlen(r) == 0 && str->returnDelims){
                r[idxR++] = str->str[str->idx];
                str->idx++;
            }
            return r;
        }
    }
    return r;
}

int hasMoreTokens(tok_str* str){
    return str->len - str->idx;
}

/*
int main(int argc, char const *argv[])
{
    tok_str* s = newTokenStr("oi,thcau.cade", ",.", 1);
    while(hasMoreTokens(s)){
        printf("%s\n", nextToken(s));
    }
    return 0;
}
*/
