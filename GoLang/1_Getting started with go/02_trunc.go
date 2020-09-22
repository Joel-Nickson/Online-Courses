package main

import "fmt"

func main() {
    var x float64;
    fmt.Scan(&x)
    var num1 int=int(x);
    fmt.Printf("num1 ==> %d\n",num1)
   
    fmt.Scan(&x)
    var num2 int=int(x);
    fmt.Printf("num1 ==> %d",num2)
}