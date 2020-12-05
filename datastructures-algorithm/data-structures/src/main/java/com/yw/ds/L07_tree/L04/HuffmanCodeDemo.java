package com.ds.L07_tree.L04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * @author yangwei
 * @date 2020-06-07 22:19
 */
public class HuffmanCodeDemo {
    private static class Node implements Comparable<Node> {
        /**
         * 存数据本身，比如 'a' => 97, ' ' => 32
         */
        private Byte data;
        /**
         * 权值
         */
        private int weight;
        private Node left;
        private Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{data: " + data + ", weight: " + weight + "}";
        }

        @Override
        public int compareTo(Node o) {
            // 从小到大排序
            return this.weight - o.weight;
        }
    }

    private static class HuffmanCode {
        /**
         * 构建赫夫曼树
         */
        private Node buildHuffmanTree(List<Node> nodes) {
            while (nodes.size() > 1) {
                // 排序：从小到大
                Collections.sort(nodes);
                // 取出根结点权值最小的两颗二叉树
                Node left = nodes.get(0);
                Node right = nodes.get(1);
                // 构建一颗新的二叉树：只有权值，没有data
                Node parent = new Node(null, left.weight + right.weight);
                parent.left = left;
                parent.right = right;
                // 从ArrayList中删除掉处理过的二叉树
                nodes.remove(left);
                nodes.remove(right);
                // 将parent加入到nodes
                nodes.add(parent);
            }
            // 返回赫夫曼树的root结点
            return nodes.get(0);
        }

        /**
         * 前序遍历
         */
        private void preOrder(Node node) {
            System.out.printf("%s\t", node.toString());
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }

        /**
         * 生成赫夫曼树对应的赫夫曼编码
         * 思路：
         * 1、将赫夫曼编码存放在 Map<Byte, String> 形式：32->01 97->100 100->11000 ...
         * 2、在生成赫夫曼编码时，需要去拼接路径，定义一个StringBuilder存储某个叶子结点的路径
         */
        static Map<Byte, String> huffmanCodeMap = new HashMap<>();
        static StringBuilder pathCode = new StringBuilder();

        private Map<Byte, String> getCodes(Node root) {
            if (root == null) {
                return null;
            }
            // 处理左子树
            getCodes(root.left, "0", pathCode);
            // 处理右子树
            getCodes(root.right, "1", pathCode);
            return huffmanCodeMap;
        }

        /**
         * 将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodeMap 集合
         *
         * @param node   传入结点
         * @param code   路径: 左子结点是 0, 右子结点 1
         * @param pathSb 用于拼接路径
         */
        private void getCodes(Node node, String code, StringBuilder pathSb) {
            StringBuilder sb = new StringBuilder(pathSb);
            sb.append(code);
            if (node != null) {
                if (node.data == null) {
                    // 非叶子结点，则递归处理
                    // 向左递归
                    getCodes(node.left, "0", sb);
                    // 向右递归
                    getCodes(node.right, "1", sb);
                } else {
                    // 叶子结点，则表示找到了某个叶子结点的最后
                    huffmanCodeMap.put(node.data, sb.toString());
                }
            }
        }

        /**
         * 编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码后的byte[]
         *
         * @param bytes          元素的字符串对应的字节数组
         * @param huffmanCodeMap 生成的赫夫曼编码map
         */
        private byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
            // 利用huffmanCodeMap将bytes转为赫夫曼编码对应的字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(huffmanCodeMap.get(b));
            }
//            System.out.println("测试 sb = " + sb.toString());
            // 将"1010100010111111110010001011..."转为byte[]
            int sbLen = sb.length();
//            int len = sbLen % 8 == 0 ? sbLen / 8 : (sbLen / 8 + 1);
            int len = (sbLen + 7) / 8;
            // 创建存储压缩后的byte数组
            byte[] huffmanCodeBytes = new byte[len];
            int index = 0;
            for (int i = 0; i < sbLen; i += 8) {
                String subStr = (i + 8 > sbLen) ? sb.substring(i) : sb.substring(i, i + 8);
                // 将subStr转成一个byte，放入到huffmanCodeBytes
                huffmanCodeBytes[index++] = (byte) Integer.parseInt(subStr, 2);
            }
            return huffmanCodeBytes;
        }

        /**
         * 封装一个赫夫曼编码的方法
         *
         * @param content 原始的字符串
         * @return 经过赫夫曼编码处理后的字节数组
         */
        private byte[] huffmanZip(String content) {
            if (content == null || content.length() == 0) {
                return new byte[0];
            }
            // 1、得到原始字符串对应的字节数组的Node集合
            byte[] bytes = content.getBytes();
            return huffmanZip(bytes);
        }

        private byte[] huffmanZip(byte[] bytes) {
            List<Node> nodes = getNodes(bytes);
            // 2、根据 nodes 创建赫夫曼树
            Node huffmanRoot = buildHuffmanTree(nodes);
            // 3、生成对应的赫夫曼编码表
            Map<Byte, String> huffmanCodeMap = getCodes(huffmanRoot);
            // 4、根据生成的赫夫曼编码，压缩得到赫夫曼编码后的字节数组
            return zip(bytes, huffmanCodeMap);
        }

        /**
         * @param bytes
         * @return
         */
        private List<Node> getNodes(byte[] bytes) {
            // 遍历bytes，统计每一个byte出现的次数 => map
            Map<Byte, Integer> counter = new HashMap<>(16);
            for (byte b : bytes) {
                Integer count = counter.get(b);
                counter.put(b, count == null ? 1 : (count + 1));
            }
            List<Node> nodes = new ArrayList<>();
            // 将counter中每一个K-V转为一个Node对象，并加入到nodes集合中
            for (Map.Entry<Byte, Integer> entry : counter.entrySet()) {
                nodes.add(new Node(entry.getKey(), entry.getValue()));
            }
            return nodes;
        }

        private byte[] decode(byte[] huffmanBytes) {
            return decode(huffmanCodeMap, huffmanBytes);
        }

        /**
         * 完成对压缩数据的解码
         *
         * @param huffmanCodeMap 赫夫曼编码表
         * @param huffmanBytes   赫夫曼编码得到的字节数组
         * @return 原来的字符串对应的数组
         */
        private byte[] decode(Map<Byte, String> huffmanCodeMap, byte[] huffmanBytes) {
            // 先得到huffmanBytes对应的二进制的字符串，形式如:10101000101111111...
            StringBuilder sb = new StringBuilder();
            int len = huffmanBytes.length;
            for (int i = 0; i < len; i++) {
                sb.append(byte2BitString(huffmanBytes[i], i != len - 1));
            }
//            System.out.println("赫夫曼编码得到的字节数组对应的二进制的字符串："+sb.toString());
            // 将字符串按照指定的赫夫曼编码进行解码
            // 把赫夫曼编码表反转
            Map<String, Byte> map = new HashMap<>();
            for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
                map.put(entry.getValue(), entry.getKey());
            }
            // 创建一个集合，存放byte
            List<Byte> list = new ArrayList<>();
            for (int i = 0; i < sb.length(); ) {
                int counter = 1;
                boolean flag = true;
                Byte b = null;
                while (flag) {
                    String key = sb.substring(i, i + counter);
                    b = map.get(key);
                    if (b == null) {
                        counter++;
                    } else {
                        flag = false;
                    }
                }
                list.add(b);
                i += counter;
            }
            // 把list中的数据放入到byte[]中
            byte[] bytes = new byte[list.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = list.get(i);
            }
            return bytes;
        }

        /**
         * 将一个byte转成一个二进制字符串
         *
         * @param b
         * @param flag 标识是否需要补高位，true需要，false不需要
         * @return b对应的二进制的字符串，注意是按补码返回的
         */
        private String byte2BitString(byte b, boolean flag) {
            // 定义一个临时变量保存b
            int temp = b;
            // 如果是正数需要补高位，和256(1 0000 0000)按位或
            if (flag) {
                temp |= 256;
            }
            String str = Integer.toBinaryString(temp);
            return flag ? str.substring(str.length() - 8) : str;
        }

        /**
         * 压缩文件
         */
        private void zipFile(String srcFile, String destFile) {
            // 创建文件的输入流、对象输出流
            try (FileInputStream fis = new FileInputStream(srcFile);
                 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destFile))) {
                // 创建一个和源文件大小一样的byte[]
                byte[] bytes = new byte[fis.available()];
                // 读取文件
                fis.read(bytes);
                // 直接对源文件进行压缩
                byte[] huffmanBytes = huffmanZip(bytes);
                // 以对象流的形式写入赫夫曼编码
                oos.writeObject(huffmanBytes);
                // 注意：一定要把赫夫曼编码表也写入，为了以后我们恢复文件时使用
                oos.writeObject(huffmanCodeMap);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * 解压文件
         */
        private void unZipFile(String zipFile, String destFile) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(zipFile));
                 FileOutputStream fos = new FileOutputStream(destFile)) {
                // 读取byte数组
                byte[] huffmanBytes = (byte[]) ois.readObject();
                // 读取赫夫曼编码表
                Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();
                // 解码
                byte[] bytes = decode(huffmanCodeMap, huffmanBytes);
                // 将bytes写入到目标文件
                fos.write(bytes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //    public static void main(String[] args) {
//        // 分解步骤
//        /*
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);
//
//        HuffmanCode huffmanCode = new HuffmanCode(nodes);
//        Node node = huffmanCode.buildHuffmanTree();
//        System.out.println("创建的赫夫曼树-前序遍历：");
//        huffmanCode.preOrder(node);
//        System.out.println();
//
//        Map<Byte, String> huffmanCodeMap = huffmanCode.getCodes(node);
//        System.out.println("测试生成的赫夫曼编码表：" + huffmanCodeMap);
//
//        byte[] huffmanCodeBytes = huffmanCode.zip(contentBytes, huffmanCodeMap);
//        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes))
//         */
//        String content = "i like like like java do you like a java";
//        HuffmanCode huffmanCode = new HuffmanCode();
//        byte[] huffmanZip = huffmanCode.huffmanZip(content);
//        System.out.println("huffmanZip = " + Arrays.toString(huffmanZip));
//
////        System.out.println(huffmanCode.byte2BitString((byte) -88, true));
//
//        byte[] sourceBytes = huffmanCode.decode(huffmanZip);
//        System.out.println(sourceBytes.length);
//        System.out.println("原始的的字符串：" + new String(sourceBytes));
//    }
    public static void main(String[] args) {
//        // 测试文件压缩
//        HuffmanCode huffmanCode = new HuffmanCode();
//        String srcFile = "/Users/yangwei/Desktop/picture/src.bmp";
//        String destFile = "/Users/yangwei/Desktop/picture/src.zip";
//        huffmanCode.zipFile(srcFile, destFile);
        // 测试文件解压
        HuffmanCode huffmanCode = new HuffmanCode();
        String zipFile = "/Users/yangwei/Desktop/picture/src.zip";
        String destFile = "/Users/yangwei/Desktop/picture/newSrc.bmp";
        huffmanCode.unZipFile(zipFile, destFile);
    }
}
