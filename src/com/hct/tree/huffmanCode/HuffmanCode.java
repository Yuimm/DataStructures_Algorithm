package com.hct.tree.huffmanCode;

import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/**
 * @Author HCT
 * @Date 2021/3/23 13:25
 * @Version 1.0
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String str = "i like like like java do you like a java";
//        byte[] contentBytes = str.getBytes();
//
//        byte[] bytes = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(bytes));
//        byte[] decode = decode(huffmanCodes, bytes);
//        System.out.println(new String(decode));

        //测试文件压缩
//        String srcFile = "h://01.jpg";
//        String dstFile = "h://dst.zip";
//        ZipFile(srcFile, dstFile);
//        System.out.println("已经压缩");

        //测试文件解压
        String zipFile = "h://dst.zip";
        String dstFile = "h://02.jpg";
        unZipFile(zipFile,dstFile);
        System.out.println("解压成功");

    }




    /**
     * 将一个文件进行压缩
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void ZipFile(String srcFile, String dstFile) {
        // 创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        // 创建文件的输入流
        FileInputStream is = null;

        try {
            /// 创建文件的输入流
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);

            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);

            // 把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            // 注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }

        }
    }


    /**
     * 完成对压缩文件的解压
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和 is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }

    }



    /**
     *  对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
 /**
        // 1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制的字符串
        for ( int i = 0; i < huffmanBytes.length; i++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
*/

        // 1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();

        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length - 1; i++) {
            byte b = huffmanBytes[i];
            String strToAppend = byteToBitString(b);
            // 判断是不是最后一个字节
            boolean isLastByte = (i == huffmanBytes.length - 2);
            if (isLastByte) {
                // 得到最后一个字节的有效位数
                byte validBits = huffmanBytes[huffmanBytes.length - 1];
                strToAppend = strToAppend.substring(0, validBits);
            }
            stringBuilder.append(strToAppend);
        }



        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 1010100010111...
                // 递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);// i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {// 说明没有匹配到
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;// i 直接移动到 count
        }
        // 当for循环结束后，我们list中就存放了所有的字符 "i like like like java do you like a java"
        // 把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;


    }


    /**
     *
     * @param flag flag标志 表示是否需要补高位，如果是true，需要补高位，否则不补，如果是最后一个字节不需要补高位
     * @param b 传入的byte
     * @return 是该b对应的二进制字符转（补码返回）
     */
/**
    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp = b; //将b转成int
        //如果是正数还需要补高位
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }
*/

// 将 byte 转换为对应的字符串
private static String byteToBitString(byte b) {
    // 使用变量保存 b
    int temp = b; // 将 b 转成 int
    temp |= 0x100; // 如果是正数我们需要将高位补零
    // 转换为二进制字符串，正数：高位补 0 即可，然后截取低八位即可；负数直接截取低八位即可
    // 负数在计算机内存储的是补码，补码转原码：先 -1 ，再取反
    String binaryStr = Integer.toBinaryString(temp);
    return binaryStr.substring(binaryStr.length() - 8);
}



    private static void preOrder(NodeCode root){
        if (root != null){
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空！");
        }
    }

    // 使用一个方法，将前面的方法封装起来，便于我们的调用.
    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //创建结点列表
        List<NodeCode> nodes = getNodes(bytes);
        // 根据 nodes 创建的赫夫曼树
        NodeCode huffmanTreeRoot = createHuffmanTree(nodes);
        // 对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }



    //编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
    /**
     *
     * @param bytes 这是原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     *
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1.利用 huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 统计返回 byte[] huffmanCodeBytes 长度
        // 一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        byte countToEight = (byte) (stringBuilder.length() % 8);
        if (countToEight == 0){
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 +1;
            //最后一个不满8位的字节补0
            for(int i = countToEight; i < 8; i++){
                stringBuilder.append('0');
            }
        }
        // 创建 存储压缩后的 byte数组，huffmanCodeBytes[len]记录赫夫曼编码最后一个字节的有效位数
        byte[] huffmanCodeBytes = new byte[len + 1];
        huffmanCodeBytes[len] = countToEight;
        int index = 0;// 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为是每8位对应一个byte,所以步长 +8
            String strByte;
            strByte = stringBuilder.substring(i, i + 8);
            // 将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;


/**
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;// 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i+8 > stringBuilder.length()) { //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;

        }

        return huffmanCodeBytes;
 */
    }



    // 为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(NodeCode root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.left, "0", new StringBuilder());
        // 处理root的右子树
        getCodes(root.right, "1", new StringBuilder());
        return huffmanCodes;

    }



    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     */
    //1.思路:将赫夫曼编码表存放在 Map<Byte,String> 形式
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //2.在生成赫夫曼编码表时，需要去拼接路径没定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的nodeCode结点的福哦有叶子节点的赫夫曼编码得到，并放入到huffmanCode集合
     * @param nodeCode 传入的结点
     * @param code 路径:左子节点是 0， 右子节点是 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(NodeCode nodeCode,String code,StringBuilder stringBuilder){
        // 因为对象传递的是引用，所以不能再原有的 StringBuilder 上进行操作
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入到StringBuilder1
        stringBuilder1.append(code);
        // 判断当前node 是叶子结点还是非叶子结点
        if (nodeCode != null){  // 如果node == null不处理
            if (nodeCode.data == null){  // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes(nodeCode.left, "0", stringBuilder1);
                // 向右递归
                getCodes(nodeCode.right, "1", stringBuilder1);
            } else {// 说明是一个叶子结点
                // 就表示找到某个叶子结点的最后
                huffmanCodes.put(nodeCode.data, stringBuilder1.toString());
            }
        }

    }





    // 可以通过List 创建对应的赫夫曼树
    private static NodeCode createHuffmanTree(List<NodeCode> nodeCode) {
        while (nodeCode.size() > 1){
            Collections.sort(nodeCode);
            // 取出第一颗最小的二叉树
            NodeCode leftNode = nodeCode.get(0);
            // 取出第二颗最小的二叉树
            NodeCode rightNode = nodeCode.get(1);
            // 创建一颗新的二叉树,它的根节点 没有data, 只有权值
            NodeCode parent = new NodeCode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 将已经处理的两颗二叉树从nodes删除
            nodeCode.remove(leftNode);
            nodeCode.remove(rightNode);
            // 将新的二叉树，加入到nodes
            nodeCode.add(parent);
        }
        // nodes 最后的结点，就是赫夫曼树的根结点
        return nodeCode.get(0);

    }



    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的就是 List 形式   [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     */
    private static List<NodeCode> getNodes(byte[] bytes){
        // 1创建一个ArrayList
        ArrayList<NodeCode> nodeCodes = new ArrayList<>();
        // 遍历 bytes , 统计 每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1 );
            } else {
                counts.put(b,count+1);
            }
        }
        // 把每一个键值对转成一个Node 对象，并加入到nodes集合
        // 遍历map
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodeCodes.add(new NodeCode(entry.getKey(),entry.getValue()));
        }
        return nodeCodes;
    }

}

//创建Node ,待数据和权值
class NodeCode implements Comparable<NodeCode> {
    Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    int weight; // 权值, 表示字符出现的次数
    NodeCode left;//
    NodeCode right;

    public NodeCode(Byte data, int weight) {

        this.data = data;
        this.weight = weight;
    }
    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }

    @Override
    public int compareTo(NodeCode o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}