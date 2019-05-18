#include <stdio.h>

int main(){
	int x;
	// declare memory
	klee_make_symbolic(&x, sizeof(x), "x");
	
	// 8 paths to go
	if (x == 100) x = x+100;
	else if (x == 1) x = x+1;
	else if (x == 2) x = x+2;
	else if (x == 3) x = x+3;
	else if (x == 4) x = x+4;
	else if (x == 5) x = x+5;
	else if (x == 6) x = x+6;
	else x*=2;
	printf("Final value of x is : %d\n", x);
}
