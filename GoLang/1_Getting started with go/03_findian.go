package main

import ("fmt";"strings")

func main() {
    var x,y string;
    
    fmt.Scanln(&x)
    if strings.HasPrefix(x, "i"){
      if strings.Contains(x, "a"){
        if strings.HasSuffix(x, "n"){
        fmt.Println("Found!")
        }}} else{
        fmt.Println("Not Found!")
    }
    fmt.Scanln(&y)
    if strings.HasPrefix(y, "i"){
      if strings.Contains(y, "a"){
        if strings.HasSuffix(y, "n"){
        fmt.Println("Found!")
        }}} else{
        fmt.Println("Not Found!")
    }
   
}