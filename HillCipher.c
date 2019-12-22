#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define MAXLEN 1000

struct HillCipher {
    char *plainText;
    int *keyMatrix;
    int *letterPos;
};

typedef struct HillCipher Cipher;

void removeSpaces(char* oldStr) {
  const char* newStr = oldStr;
  do { *oldStr = *newStr; if (*oldStr != ' ') oldStr++; } while (*newStr++ != 0);
}

void lowerCase(char* oldStr) {
  const char* newStr = oldStr;
  do { *oldStr = tolower(*newStr); oldStr++; } while (*newStr++ !=0);
}

void appendIfOdd(char* oldStr){
    /* append() adds an additional character if the length is odd */
    const int length = strlen(oldStr);
    for (int ii=0; ii < length; ++ii) {
        if (length % 2 != 0) oldStr[length-1] = 'e';
    }
}


void checkDelimiters(char* keyMatrix) {
    const char replaceWithChar = 'D';
    for(int ii=0; ii < strlen(keyMatrix); ++ii) {
      if (keyMatrix[ii] == ' ') keyMatrix[ii] = replaceWithChar;
      if (keyMatrix[ii] == ',') keyMatrix[ii] = replaceWithChar;
      if (keyMatrix[ii] == '.') keyMatrix[ii] = replaceWithChar;
    }

}

void parsePlainText(char* plainText) {
  removeSpaces(plainText);
  lowerCase(plainText);
  appendIfOdd(plainText);
}

void parseKeyMatrix(char* keyMatrix) {
   checkDelimiters(keyMatrix);
}

void generateDigraphs(char* tempPlainText, char* tempKeyMatrix) {
      /* pointers for generating the digraphs */
      int *xi = NULL;
      int *yi = NULL;
      int xval, yval;
      int digraphX;
      int digraphY;

      /* storage structure for the cipher data */
      Cipher *result = malloc(sizeof(struct HillCipher));

      /* allocate each member of the result data structure */
      result->plainText = malloc(sizeof(char) * strlen(tempPlainText));
      result->keyMatrix = malloc(sizeof(int) * strlen(tempKeyMatrix));
      result->letterPos = malloc(sizeof(int) * strlen(tempPlainText));

      /* assign the plain text into the result cipher structure */
      for (int chr=0; chr < strlen(tempPlainText); ++chr) {
          char tempChar = tempPlainText[chr];
          result->plainText[chr] = tempChar;
      }

      /* convert the key matrix into an integer array */
      for (int key=0; key < strlen(tempKeyMatrix); ++key) {
          int tempKey = atoi(&tempKeyMatrix[key]);
          result->keyMatrix[key] = tempKey;
      }

      /* find the number equivalent of each letter */
      for (int letter=0; letter < strlen(tempPlainText); ++letter) {
         result->letterPos[letter] = tempPlainText[letter] - 'a' + 1;
      }

      /* allocate memory for each digraph - digraphX and digraphY */
      xi = malloc (sizeof(int) * (sizeof(result->letterPos)/2));
      yi = malloc (sizeof(int) * (sizeof(result->letterPos)/2));

      /* generate the digraphs for both X and Y */
      for (int nn=0; nn < sizeof(result->letterPos)/2; ++nn) {
        xi[nn] = nn*2;
        yi[nn] = xi[nn]+1;

        xval = result->letterPos[xi[nn]];
        yval = result->letterPos[yi[nn]];

        digraphX = (xval * result->keyMatrix[0] + result->keyMatrix[1] * yval) % 26;
        digraphY = (xval * result->keyMatrix[2] + result->keyMatrix[3] * yval) % 26;

        printf("(%d %d)", digraphX, digraphY);
      }

      printf("\n"); 


      /* free the results data structure members */
      free(xi);
      free(yi);
      free(result->plainText);
      free(result->keyMatrix);
      free(result->letterPos);
      free(result);
}

void encrypt(char* tempPlainText, char* tempKeyMatrix) {

    /* prepare the plaintext for usage */
    parsePlainText(tempPlainText);

    /* prepare the keymatrix for usage */
    parseKeyMatrix(tempKeyMatrix);

    /* generate digraphs from the prepared plaintext and matrix */
    generateDigraphs(tempPlainText, tempKeyMatrix);

}

void prompt(char *message, char *outputStream) {
    char tempLine[MAXLEN];
    printf("%s", message);

    while (fgets(tempLine, MAXLEN, stdin) != NULL) {
        int lineLength = strlen(tempLine);

        if (lineLength == 1) {
          printf("ERROR: invalid data entered.\n");
        } else {
          strcpy(outputStream, tempLine);
          break;
        }
    }
}

int main() {
   char tempPlainText[MAXLEN];
   char tempKeyMatrix[MAXLEN];

   prompt("Enter plain text: \n", tempPlainText);
   prompt("Enter key matrix (separated by comma or spaces) \n", tempKeyMatrix);

   encrypt(tempPlainText, tempKeyMatrix);

   return 0;
}
