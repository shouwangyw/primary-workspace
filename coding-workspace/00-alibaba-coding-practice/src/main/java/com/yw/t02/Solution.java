package com.yw.t02;

import java.util.Scanner;

/**
 * Created by hp on 2019/5/6.
 */
public class Solution {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int maxQps = in.nextInt();
        String[] rtList = in.nextLine().split(",");
        int requestNum = in.nextInt();
        int threadNum = in.nextInt();
        System.out.println(doneTime(maxQps, rtList, requestNum, threadNum));
    }
    /**
     * 如果使用最优的最大吞吐量负载均衡算法，按照最优模型多久能够处理完所有请求，单位毫秒
     * @param maxQps        每台broker的极限QPS
     * @param rtList        broker rt列表,用逗号分割，几个rt表示几个broker
     * @param requestNum    消息生产请求总数
     * @param threadNum     最大并发线程数
     * @return
     */
    static long doneTime(int maxQps,String[] rtList,int requestNum,int threadNum) {
        int qpsSum = 0;
        for (String rtString : rtList) {
            int singleMaxQps = threadNum * 1000 / Integer.valueOf(rtString);
            if (singleMaxQps > maxQps) {
                qpsSum += maxQps;
            }else {
                qpsSum += singleMaxQps;
            }
        }

        return requestNum / qpsSum * 1000;
    }
}
