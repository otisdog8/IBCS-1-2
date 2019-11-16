#include<iostream>

using namespace std;

bool primetest(int);
int main(){

int a = 0;

cout<< "Enter a number: " ;
cin >> a ;


if(primetest(a)){
    cout << "True" ;
}
else{
    cout << "false" ;
}
}
bool primetest(int A){
int P = 0;
int N = 2;
while (true){
 P = (A % N );
 if (P == 0 or A == 1){
    break ;
 }
  N = N + 1;
}
if (N == A){
    return true ;
}
else{
    return false ;
}

}