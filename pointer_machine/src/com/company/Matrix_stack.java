package com.company;

import java.util.Stack;

public class Matrix_stack {

    Stack<Matrix> stack=new Stack<>();
    Matrix_stack()
    {
        stack.push(Matrix.iden());

    }
    void push(Matrix m)
    {

        stack.push(stack.peek().mul(m));

    }
    Matrix top()
    {
        return stack.peek();
    }
    void pop()
    {

        stack.pop();

    }
    void clear()
    {
        stack.clear();
        stack.push(Matrix.iden());

    }

}
