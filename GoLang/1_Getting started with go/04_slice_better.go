package main

import ("fmt"
  "strconv"
  "sort")

func main() {
  //sl := []int {5,7,3}
  sl := make([]int ,0 ,3)
  var n string
  for {
    fmt.Scan(&n)
    v, err := strconv.ParseInt(n , 10,64)
    if err==nil{
      sl = append(sl ,int(v))
      sort.Ints(sl)
      fmt.Println(sl)
    }else if n=="x"||n=="X"{
      break
    }else{
      fmt.Println("****Try Again****")
    } 
  }
}