package com.company;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.ObjIntConsumer;

public class HuffmanCode {
    public static void main(String[] args) {
        String content="i like like like java do you like a java";
        byte[] contentBytes=content.getBytes();
        List<Node> list=getNodes(contentBytes);

        System.out.println("nodes="+list);
        //通过List创建对应的赫夫曼树
        Node huffmanTreeRoot=createHuffmanTree(list);
        System.out.println("前序遍历:");
        preOrder(huffmanTreeRoot);
        //测试是否生成了对应的赫夫曼编码,头结点对应的编码为空
        Map<Byte,String> huffmanCodes=getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表为"+HuffmanCodes);
        //byte[] huffmanCodeBytes=zip(contentBytes,huffmanCodes);




        byte[] huffmanCodeBytes=huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));

        byte[] sourceBytes=decode(huffmanCodes,huffmanCodeBytes);
        System.out.println("原来的字符串"+new String(sourceBytes));
    }
    /**
     * 编写方法，将一个文件进行压缩
     */
    public static void zipFile(String srcFile,String destFile,Map<Byte,String> huffmanCodes){
        //创建文件输入流
        FileInputStream in=null;
        //创建文件输入流
        FileOutputStream out=null;
        //创建对象输出流
        ObjectOutputStream oos=null;
        try{
            in=new FileInputStream(srcFile);
            //创建和源文件大小一样的byte
            byte[] b=new byte[in.available()];
            //读取文件
            in.read();
            //直接对源文件压缩
            byte[] huffmanBytes=huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            out= new FileOutputStream(destFile);
            //对象输出流
            oos=new ObjectOutputStream(out);
            //把huffmanBytes写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复文件时使用
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                in.close();
                out.close();
                oos.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }


    }
    //编写一个方法，完成对压缩文件的解压
    public static void unZipFile(String zipFile,String destFile) {
        //定义文件输入流
        InputStream in=null;
        //定义一个对象输入流
        ObjectInputStream ois=null;
        //定义文件输出流
        OutputStream os=null;
        try{
            //创建文件输入流
            in=new FileInputStream(zipFile);
            //创建一个和in相关联的对象输入流
            ois=new ObjectInputStream(in);
            //读取压缩后byte数组
            byte[] huffmanBytes=(byte[])ois.readObject();
            //读取保存的赫夫曼编码表
            Map<Byte,String> huffmanCodes=(Map<Byte,String> )ois.readObject();
            //解码
            byte[] bytes=decode(huffmanCodes,huffmanBytes);
            //将bytes写入到目标文件
            os=new FileOutputStream(destFile);
            //写数据到文件中
            os.write(bytes);

        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally {
            try {
                os.close();
                ois.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
         byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 完成数据的解压：
     * 1，将huffmanCodeBytes先转成赫夫曼编码对应的二进制的字符串“101010001011111~”
     * 2，将二进制的字符串--------通过对应的赫夫曼编码---》》》"i like like java do~"
     */
    //2,编写一个方法，完成对压缩数据的解码
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //1,
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<huffmanBytes.length;i++){
            byte b=huffmanBytes[i];
            boolean flag=(i==huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，因为反向查询
        Map<String,Byte> map=new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //创建集合，存放byte
        List<Byte> list=new ArrayList<>();
        for(int i=0;i<stringBuilder.length();){
            int count=1;//双指针，i不动，让count动
            boolean flag=true;
            Byte b=null;
            while(flag){
                String key=stringBuilder.substring(i,i+count);
                b=map.get(key);
                if(b==null){
                    //说明没有找到
                    count++;
                }else{
                    flag=false;
                }
            }
            list.add(b);
            i+=count;//i直接移动到count
        }
        //当for循环结束后，我们list中就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] b=new byte[list.size()];
        for(int i=0;i<b.length;i++){
            b[i]=list.get(i);
        }
        return b;
    }

    //将一个byte转成一个二进制的字符串，返回的是byte对应的二进制的字符串(补码)
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp=b;//将b转为int
        if(flag){
            //最后一个字节不需补高位
            temp|=256;//如果是正数我们补高位
        }
        String str=Integer.toBinaryString(temp);//返回的是对应的二进制的补码
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }
    /**
     * 编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     * 1,利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
     * 2,将“101010001011111~”转成byte[]
     */

    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
       //1,
        StringBuilder stringBuilder=new StringBuilder();
       for(byte b:bytes){
           stringBuilder.append(huffmanCodes.get(b));
       }
       //2,
        int len=(stringBuilder.length()+7)/8;//字符串%8==0
        //创建存储压缩后的byte[]
        byte[] huffmanCodeBytes=new byte[len];
        int index=0;//记录是第几个byte
        for(int i=0;i<stringBuilder.length();i+=8){
            String strByte;
            if(i+8>stringBuilder.length()){//不够8位
                strByte=stringBuilder.substring(i);
            }else{
                strByte=stringBuilder.substring(i,i+8);
            }
            //将strByte转成一个byte，放到huffmanCodeBytes
            huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte,2);//将二进制数转为10进制
            index++;
        }
        return huffmanCodeBytes;
    }

    //接受一个字节数组，返回的就是List [date,count]
    private static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes=new ArrayList<>();
        //遍历数组，统计每一个byte出现的次数 map[key,value]
        Map<Byte, Integer> counts=new HashMap<>();
        for(byte b:bytes){
            Integer count=counts.get(b);
            if(count==null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //把每个键值队转成一个Node对象，并加入到nodes集合
        for(Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    //通过list创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);
            Node parent=new Node(null,leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
    //前序遍历
    private static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("赫夫曼树为空");
        }
    }
    /**
     * 生成赫夫曼树对应的赫夫曼编码表
     * 1，将赫夫曼编码表存放在Map<Byte,String>形式，Byte 字符--String 字符路径
     * 2，在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder，存储某个叶子结点的路径
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到HuffmanCodes
     */
      //重载getCodes
      private static Map<Byte,String> getCodes(Node root){
          if(root==null){
              return null;
          }
          //处理root的左子树
          getCodes(root.left,"0",stringBUilder);
          //处理右子树
          getCodes(root.right,"1",stringBUilder);
          return HuffmanCodes;

      }
      static Map<Byte,String> HuffmanCodes=new HashMap<Byte,String>();
      static StringBuilder stringBUilder=new StringBuilder();
      //code路径
      private static void getCodes(Node node,String code,StringBuilder stringBUilder){
          StringBuilder stringBuilder2=new StringBuilder(stringBUilder);
          //将code加入到stringBuilder2
          stringBuilder2.append(code);
          if(node!=null){
              //判断当前结点时叶子结点还是非叶子结点
              if(node.data==null){
                  //递归处理
                  //向左递归
                  getCodes(node.left,"0",stringBuilder2);
                  //向右递归
                  getCodes(node.right,"1",stringBuilder2);
              }else{
                  //当前结点是一个叶子结点,表示找到了某个叶子节点的整个路径
                  HuffmanCodes.put(node.data,stringBuilder2.toString());
              }
          }
      }

}
//创建Node,有数据和权值
class Node implements Comparable<Node>{
    Byte data;//存放数据(字符本身)
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data,int weight) {
        this.data = data;
        this.weight=weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node["  + "data=" + data + ",weight=" + weight +   ']';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}