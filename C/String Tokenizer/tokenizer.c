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
    r[idxR++] = str->str[str->idx++];
    while(hasMoreTokens(str) &&
          strchr(str->delims, str->str[str->idx-1]) == NULL){
        if(strchr(str->delims, str->str[str->idx]) == NULL){
            r[idxR++] = str->str[str->idx++];
        }else{
            str->idx += str->returnDelims?0:1;
            if(strlen(r) > 0){
                return r;
            }else{
                r[idxR++] = str->str[str->idx];
                return r;
            }
        }

    }
    return r;
}

int hasMoreTokens(tok_str* str){
    return str->len - str->idx;
}
