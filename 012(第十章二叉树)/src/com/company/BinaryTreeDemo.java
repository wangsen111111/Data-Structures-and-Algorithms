package com.company;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree=new BinaryTree();
        //创建需要的节点
        HeroNode root=new HeroNode(1,"王森");
        HeroNode node2=new HeroNode(2,"李知恩");
        HeroNode node3=new HeroNode(3,"王祖贤");
        HeroNode node4=new HeroNode(4,"拉宏桑");
        HeroNode node5=new HeroNode(5,"拉加桑");
        //先手动创建二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        //测试
        //前序遍历
        System.out.println("前序遍历:");
        binaryTree.preOrder();
        //中序遍历
        System.out.println("中序遍历:");
        binaryTree.infixOrder();
        //后序遍历
        System.out.println("后序遍历:");
        binaryTree.postOrder();
        //前序遍历查找
        System.out.println("前序遍历查找：");
        HeroNode resNode=binaryTree.preOrderSearch(10);
        if(resNode!=null){
            System.out.printf("找到了，信息为no=%d name=%s\n",resNode.getNo(),resNode.getName());

        }else{
            System.out.printf("没有找到no=%d的英雄",10);
        }
        //中序遍历查找
        System.out.println("中序遍历查找：");
        HeroNode resNodeInfix=binaryTree.infixOrderSearch(5);
        if(resNodeInfix!=null){
            System.out.printf("找到了，信息为no=%d name=%s\n",resNodeInfix.getNo(),resNodeInfix.getName());

        }else{
            System.out.printf("没有找到no=%d的英雄",resNodeInfix.getNo());
        }
        //后序遍历查找
        System.out.println("后序遍历查找：");
        HeroNode resNodePost=binaryTree.postOrderSearch(5);
        if(resNodePost!=null){
            System.out.printf("找到了，信息为no=%d name=%s\n",resNodePost.getNo(),resNodePost.getName());

        }else{
            System.out.printf("没有找到no=%d的英雄",resNodePost.getNo());
        }
    }
}
//定义BinaryTree二叉树
class BinaryTree{
    private HeroNode root;//根节点
    public void setRoot(HeroNode root){
        this.root=root;
    }
    //删除节点(特殊情况)
    public void delNode(int no){
        if(root!=null){
            //root是不是就是要删除的节点
            if(root.getNo()==no){
                root=null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }
    }
    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

}
//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    /**
     * 递归删除节点
     * 1，如果删除的节点是叶子节点，则删除该节点
     * 2，如果删除的节点是非叶子节点，则删除该子树
     */
    public void delNode(int no){
        if(this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }

    }
    //前序遍历
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //如果左子节点不为空，则递归继续前序遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //如果右子节点不为空，递归继续前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //如果左子节点不为空，继续中序遍历
        if(this.left!=null){
            this.left.infixOrder();
        }
        //输出当前节点
        System.out.println(this);
        //如果右子节点不为空，则递归继续中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //如果左子节点不为空，继续后续遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //如果右子节点不为空，继续后续遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        //输出当前节点
        System.out.println(this);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.name=name;
        this.no = no;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name=" + name+  '}';
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        //比较当前节点是不是
        if(this.no==no){
            return this;
        }
        HeroNode resNode=null;
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        if(this.left!=null){
            //如果左递归前序查找，找到节点，则返回
            resNode=this.left.preOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        //否则继续判断，当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        //判断当前节点的右子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        //如果找到则返回，如果没有找到，就和当前节点比较，如果是就返回当前节点
        if(this.no==no){
            return this;
        }
        //否则，如果当前节点的右子节点不为空，则递归中序查找
        if(this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        //判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        //如果没找到,右子节点不为空，递归后序遍历
        if(this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        //如果左右子树都没有找到，就比较当前节点是不是
        if(this.no==no){
            return this;
        }
        return resNode;
    }
}
