package main

import (
	"fmt"
	)

func Swap(a,b int) (int,int){
	t:=a
	a=b
	b=t
	return a,b
}

func BubbleSort(sli []int,leng int){
	for i:=0 ;i<leng-1 ;i++ {
		for j:=0 ;j<leng-i-1 ;j++{
			if sli[j]>sli[j+1]{
				sli[j],sli[j+1]=
				Swap(sli[j],sli[j+1])
			}
		}
	}
}

func main(){
	sli := make([]int ,0,10)
	//fmt.Println(cap(sli))
	var n int
	for i:=0 ;i<cap(sli)/*10*/ ;i++ {
		fmt.Scanln(&n)
		sli=append(sli,n)
	}
	BubbleSort(sli,len(sli))
	fmt.Println(sli)
}