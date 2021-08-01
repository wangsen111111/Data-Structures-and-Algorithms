package com.company;

/**
 * 栈实现综合计算器： 数栈(numStack)存放数    符号栈(operStack)存放运算符
 * (1)通过一个index值（索引），来遍历我们的表达式
 * (2)如果发现这是一个数字，就直接加入数栈numStack中
 * (3)如果发现是一个符号
 *    1.1，如果当前符号栈operStack为空，就直接入栈
 *    2.2，如果符号栈operStack有操作符就进行比较
 *        1,如果当前操作符的优先级大于栈中操作符operStack，就直接加入到符号栈operStack
 *        2,如果当前操作符的优先级小于或者等于栈中的操作符
 *          就需要从数栈中numStack中pop出两个数，再从符号栈operStack中pop出一个符号进行运算，将得到的结果入数栈
 *          然后将当前的操作符加入到符号栈operStack
 * (4)当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并计算
 * (5)最后在数栈numStack中只有一个数字，就是表达式的结果
 */

public class Calculator {
    public static void main(String[] args) {
        String expression = "70*8-60+20"; // 18//如何处理多位数的问题？
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operStack=new ArrayStack2(10);
        int index=0;//用于扫描的索引
        int num1=0; int num2=0;//弹栈出的数据num1和num2
        int oper=0;//用于接收操作符的oper
        int res=0;//运算得到的结果
        char ch=' ';//将每次扫描得到的char保存到ch
        String keepNum="";//用于拼接多位数
        //开始while循环的扫描expression
        while(true){
            //依次得到exception的每一个字符
            ch=expression.substring(index,index+1).charAt(0);//把字符串转为字符
            //判断ch时字符还是数字
            //是一个字符
            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    /**
                     * 如果当前符号栈不为空
                     * 1,如果当前操作符的优先级大于栈中操作符operStack，就直接加入到符号栈operStack
                     * 2,如果当前操作符的优先级小于或者等于栈中的操作符
                     *   就需要从数栈中numStack中pop出两个数，再从符号栈operStack中pop出一个符号进行运算，将得到的结果入数栈
                     *   然后将当前的操作符加入到符号栈operStack
                     */
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        //从数栈numStack中取出两个数
                        num1=numStack.pop();
                        num2=numStack.pop();
                        //再从符号栈中取出一个符号
                        oper=operStack.pop();
                        //进行运算
                        res=numStack.cal(num1,num2,oper);
                        //把运算得到的结果加入到数栈中
                        numStack.push(res);
                        //然后将当前的操作符加入到符号栈中
                        operStack.push(ch);

                    }else{
                        //如果当前的操作符的优先级大于栈中操作符operStack，就直接加入到符号栈operStack
                        operStack.push(ch);

                    }
                }else{
                    //如果当前符号栈为空，就直接入栈
                    operStack.push(ch);
                }

            }else{
                //如果是数，就直接入数栈
                //扫描到是字符'1'，和数字1相差48
                //numStack.push(ch-48);
                /**
                 * 处理多位数
                 * 1，在处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                 * 2，在处理数时，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                 * 3，因此我们需要定义一个变量字符串，用于拼接
                 */
                //先拼接ch
                keepNum+=ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，就入数栈numStack
                    //注意是看后一位，不是将index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，就直接入数栈,因为此时的keepNum是一个字符串要将它转为一个int
                        numStack.push(Integer.parseInt(keepNum));
                        //注意这个字符串已用完，后面还要使用keepNum，所以要将keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index加1，并判断是否扫描到exception最后
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //(4)当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并计算
        //(5)最后在数栈numStack中只有一个数字，就是表达式的结果
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字（结果）
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);//再将得到的结果入栈
        }
        System.out.printf("表达式%s=%d",expression,numStack.pop());
    }
}
//定义一个 ArrayStack2 表示栈
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈，数据就放在该数组中
    private int top=-1;//top表示栈顶，初始化为-1
    //构造器
    public ArrayStack2(int MaxSize){
        this.maxSize=MaxSize;
        stack=new int[this.maxSize];//对数组进行初始化
    }
    //增加一个方法，可以返回当前栈顶的值，但不是pop
    public int peek(){
        return stack[top];
    }
    //栈满
    public boolean isFull(){ return top==maxSize-1; }
    //栈空
    public boolean isEmpty(){ return top==-1; }
    //入栈--push
    public void push(int value){
        //判断栈是否已满
        if(isFull()){
            System.out.println("栈已满，不能添加到栈中~");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈--pop
    public int pop(){
        //判断栈是否为空
        if(isEmpty()){
            throw new RuntimeException("栈空~");
        }
        int value=stack[top];
        top--;
        return value;
    }
    //遍历栈，出栈是从栈顶出，从栈顶往下遍历
    public void list(){
        //判断栈是否为空
        if(isEmpty()){
            System.out.println("栈为空~");
        }
        for(int i=top;i>0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //扩展功能
    //返回运算符的优先级，//优先级自己定(优先级使用数字表示，数字越大，则优先级越高)
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;//由于目前运算式只有加减乘除
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//res用于存放计算的结果
        switch(oper){
            case'+':
                res=num2+num1;
                break;
            case'-':
                res=num2-num1;
                break;
            case'*':
                res=num2*num1;
                break;
            case'/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}
