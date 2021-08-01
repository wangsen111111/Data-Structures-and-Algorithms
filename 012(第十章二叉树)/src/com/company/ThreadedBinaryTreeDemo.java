package com.company;
/*
线索化二叉树(中序线索二叉树)
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //创建需要的节点
        HeroNode1 root=new HeroNode1(1,"王森");
        HeroNode1 node2=new HeroNode1(3,"李知恩");
        HeroNode1 node3=new HeroNode1(6,"王祖贤");
        HeroNode1 node4=new HeroNode1(8,"拉宏桑");
        HeroNode1 node5=new HeroNode1(10,"拉加桑");
        HeroNode1 node6=new HeroNode1(14,"德善");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试中序线索化
        threadedBinaryTree threadedBinaryTree = new threadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //测试: 以 10 号节点测试
        HeroNode1 leftNode = node5.getLeft();
        HeroNode1 rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 =" + leftNode);//3
        System.out.println("10 号结点的后继结点是=" + rightNode); //1
        //当线索化二叉树后，能在使用原来的遍历方法
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}
//实现了线索化的二叉树
//定义BinaryTree二叉树
class threadedBinaryTree{
    private HeroNode1 root;//根节点
    //为了实现线索化，需要创建要指向当前节点的前缀结点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode1 pre=null;
    public void setRoot(HeroNode1 root){
        this.root=root;
    }
    //重载一把threadedNodes方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //遍历中序线索化二叉树
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode1 node=root;
        while(node!=null){
            //循环找到leftType==1的结点，第一个找到的就是8结点
            //后面随着辩能力而变化，因为当leftType==1时，说明该结点时按照线索化
            //处理后面的有效结点
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            //如果当前系欸但的有指针指向的是后继结点，就一直输出
            while(node.getRightType()==1){
                //获取到当前结点的后继结点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node=node.getRight();
        }
    }
    //编写对二叉树进行中线股线索化的方法  node为当前需要线索化的结点
    public void threadedNodes(HeroNode1 node){
        if(node==null){
            return;
        }
        //(1)先线索化左子树
        threadedNodes(node.getLeft());

        //(2)线索化当前节点
        //先处理当前节点的前驱节点
        if(node.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前置节点
            node.setLeftType(1);
        }
        //再处理当前节点的后继节点
        if(pre!=null&&pre.getRight()==null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点时下一个结点的前驱结点
        pre=node;
        //(3)线索化右子数
        threadedNodes(node.getRight());

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
    public HeroNode1 preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历查找
    public HeroNode1 infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历查找
    public HeroNode1 postOrderSearch(int no){
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
//创建HeroNode
//先创建HeroNode节点
class HeroNode1{
    private int no;
    private String name;
    private HeroNode1 left;
    private HeroNode1 right;
    //leftType=0指向左子树，leftType=1指向前置节点
    //rightType=0指向右子树，rightType=1指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    public HeroNode1(int no, String name) {
        this.name=name;
        this.no = no;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name=" + name+  '}';
    }

    //前序遍历查找
    public HeroNode1 preOrderSearch(int no){
        //比较当前节点是不是
        if(this.no==no){
            return this;
        }
        HeroNode1 resNode=null;
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
    public HeroNode1 infixOrderSearch(int no){
        //判断当前节点的右子节点是否为空，如果不为空，则递归中序查找
        HeroNode1 resNode=null;
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
    public HeroNode1 postOrderSearch(int no){
        //判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode1 resNode=null;
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
