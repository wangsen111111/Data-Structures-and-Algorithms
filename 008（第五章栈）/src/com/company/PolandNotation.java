package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * (1)将一个逆波兰表达式，依次将数据和运算符，放入到ArrayList中
 * (2)完成对逆波兰表达式的计算(遍历ArrayList)
 */
public class PolandNotation {
    public static void main(String[] args) {
        //1,完成将一个中缀表达式转成后缀表达式的功能  //说明：   1+((2+3)×4)-5 => 转成 1 2 3 + 4 × + 5-
        //2. 因为直接对 str字符串扫描不方便       //即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        String expression = "1+((2+3)*4)-5";
        System.out.println("将中缀表达式放到集合中List=" + toInfixException(expression));
        //3. 将得到的中缀表达式对应的 List => 后缀表达式对应的 List
        // 即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.println("将前缀表达式转为后缀表达式：" + parseSuffixExpressionList(toInfixException(expression)));
        System.out.printf("计算：%s=%d\n",expression,calculate(parseSuffixExpressionList(toInfixException(expression))));


        //先定义一个逆波兰表达式
        //例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 -,为了方便，用空格将数字和符号隔起来
        String suffixExpression = "3 4 + 5 * 6 -";
        /**
         * 思路：1，先将3 4 + 5 × 6 -放入到ArrayList中
         *      2，将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */
        List<String> list = getListString(suffixExpression);
        System.out.println("List=" + list);
        System.out.println("计算的结果为：" + calculate(list));
    }
    //3. 将得到的中缀表达式对应的 List => 后缀表达式对应的 List
    // 即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]

    /**
     * 1) 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压 s2；
     * 4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
     * 1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
     * 3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较；
     * 5) 遇到括号时：
     * (1) 如果是左括号“(”，则直接压入 s1
     * (2) 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤 2 至 5，直到表达式的最右边
     * 7) 将 s1 中剩余的运算符依次弹出并压入 s2
     * 8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //1) 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；
        //符号栈s1
        Stack<String> s1 = new Stack<String>();
        //因为s2这个栈，中间没有使用pop操作，并且最后还需要逆序输出，因此比较麻烦，所以我们不用Stack<String>而是用List<String>
        List<String> s2 = new ArrayList<String>();
        //遍历中缀表达式
        for (String item : ls) {
            if (item.matches("\\d+")) {
                //3) 遇到操作数时，将其压 s2；
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这 一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（括号移除s1,消除小括号
            } else {
                //4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：  缺少一个比较优先级的算法
                //当 item 的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1) 与 s1 中新的栈顶运算符相比较
                while (s1.size()!=0 && getValue(item) <= getValue(s1.peek())) {
                    s2.add(s1.pop());
                }
                //1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                //     *    2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
                s1.push(item);
            }
        }
        // 7) 将 s1 中剩余的运算符依次弹出并压入 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        //注意因为是存放到 List有序的, 因此按顺序输出就是对应的后缀表达式对应的 List
        return s2;
    }

    //写一个方法getValue，返回对应的优先级数字getValue
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "*":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
            default:
                //System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

    //方法：将中缀表达式转成对应的List
    public static List<String> toInfixException(String s) {
        //定义一个list,存放中缀表达式，对应的内容
        List<String> list = new ArrayList<String>();
        //这是一个指针，用于遍历中缀表达式字符串
        int i = 0;
        //对多位数的拼接
        String str;
        //每遍历到一个字符就放到c
        char c;
        do {
            //如果c是一个非数字，就加入到list
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;//i需要后移
            } else {
                //如果是一个数，需要考虑多位数，将其进行拼接
                str = "";//先将str置为空，'0'->[48]//'9'->[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                //将拼接后的多位数加入到list
                list.add(str);
            }
        } while (i < s.length());
        return list;//返回链表
    }

    //写一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression按空格分割
        String[] split = suffixExpression.split(" ");
        //创建集合
        List<String> list = new ArrayList<String>();
        //将分割出的数据和运算符放入到ArrayList中
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }
    //完成对逆波兰表达式的计算
    //1,遍历list集合，如果是数就入栈
    //2，如果是运算符就从栈中弹出两个数，进行运算，然后将结果入栈
    //3，最后留在栈中的就是运算结果

    /**
     * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
     * 1．从左至右扫描(对list集合进行遍历)，将 3 和 4 压入堆栈；
     * 2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
     * 3．将 5 入栈；
     * 4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
     * 5．将 6 入栈；
     * 6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历list集合     /item一件物品，元素/
        for (String item : list) {
            //如果是数，就入栈(使用正则表达式)
            if (item.matches("\\d+")) {
                //匹配的是多位数，就入栈
                stack.push(item);
            } else {
                //如果是运算符，就从栈中弹出两个数，计算它们的值，然后再将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符错误~");
                }
                //然后将运算得到的值入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的是运算结果
        return Integer.parseInt(stack.pop());
    }
}