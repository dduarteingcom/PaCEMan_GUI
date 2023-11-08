//
// Created by alexi on 7/11/2023.
//
#include "ComList.h"
#include <stdio.h>
#include <malloc.h>

struct command * createCommand(char * text){
    struct command * newCommand = (struct command *)malloc(sizeof(struct command));
    newCommand->text = text;
    newCommand->nextCommand = NULL;
    return newCommand;
}

struct commandList * initializeCommandList(){
    struct commandList * newList = (struct commandList *) malloc(sizeof(struct commandList));
    int * listLength = (int *)malloc(sizeof (int));
    *(listLength) = 0;
    newList->length = listLength;
    newList->head = NULL;
    return newList;
}

void addCommandLast(struct commandList * list, struct command * newCommand){
    if(*(list->length) == 0){
        list->head = newCommand;
    }else{
        struct command * temp = list->head;
        while(temp->nextCommand != NULL ){
            temp = temp->nextCommand;
        }
        temp->nextCommand = newCommand;
    }
    (*(list->length))++;
}

void printCommandList(struct commandList * lista){
    if(*(lista->length) > 0){
        struct command * temp = lista->head;

        for (int i = 0; i < (*(lista->length)); ++i) {
            printf("Command %d: %s \n", i, temp->text);
            temp = temp->nextCommand;
        }
    }else{
        printf("Lista vacia. \n");
    }
    printf("---------------------------- \n");

}

void deleteHeadCommand(struct commandList * list){
    if(*(list->length) == 0){
        return;
    }else{
        struct command * temp = list->head;
        list->head = temp->nextCommand;
        free(temp);
    }
    (*(list->length))--;
}