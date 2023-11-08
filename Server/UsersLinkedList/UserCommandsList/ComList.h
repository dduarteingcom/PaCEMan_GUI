//
// Created by alexi on 7/11/2023.
//

#ifndef SERVER_COMLIST_H
#define SERVER_COMLIST_H

typedef struct command{
    const char * text;
    struct command * nextCommand;
}command;

typedef struct commandList{
    struct command * head;
    int * length;
}commandList;

struct command * createCommand(char * text);
struct commandList * initializeCommandList();
void addCommandLast(struct commandList * list, struct command * newCommand);
void addCommandFirst(struct commandList * list, struct command * newCommand);
void printCommandList(struct commandList * lista);
void deleteHeadCommand(struct commandList * list);

#endif //SERVER_COMLIST_H
