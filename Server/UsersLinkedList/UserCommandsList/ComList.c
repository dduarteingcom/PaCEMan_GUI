//
// Created by alexi on 7/11/2023.
//
#include "ComList.h"
#include <stdio.h>
#include <malloc.h>
#include <string.h>

/**
 * @brief Creates a command struct, with memory allocation
 * @param text string of the command
 * @return new command created
 */
struct command * createCommand(char * text){
    struct command * newCommand = (struct command *)malloc(sizeof(struct command));
    newCommand->text = text;
    newCommand->nextCommand = NULL;
    return newCommand;
}

/**
 * @brief initializes a command list
 * @return the new list initialized
 */
struct commandList * initializeCommandList(){
    struct commandList * newList = (struct commandList *) malloc(sizeof(struct commandList));
    int * listLength = (int *)malloc(sizeof (int));
    *(listLength) = 0;
    newList->length = listLength;
    newList->head = NULL;
    return newList;
}

/**
 * @brief Adds command to the bottom of the list, to follow a FIFO structure
 * @param list list of commands
 * @param newCommand new command to attach
 */
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

/**
 * @brief Adds elements to the top of the list, to follow LIFO logic
 * @param list list of commands
 * @param newCommand newcommand to attach
 */
void addCommandFirst(struct commandList * list, struct command * newCommand){
    newCommand->nextCommand = list->head;
    list->head = newCommand;
    (*(list->length))++;
}

/**
 * @brief Prints the entire list
 * @param lista list of commands
 */
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

/**
 * @brief Deletes the top of the commands
 * @param list list of commands
 */
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