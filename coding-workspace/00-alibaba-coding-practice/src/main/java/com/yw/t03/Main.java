//package com.yw.t03;
//
//import java.util.*;
//import java.util.concurrent.Executors;
//
///**
// * Created by hp on 2019/5/6.
// */
//public class Main {
//    public static void main(String[] args){
//        Solution solution = new Solution();
//        solution.createCard();
//        solution.refreshCard();
//        solution.createPlayers();
//        solution.sendCard();
//        solution.startGame();
//    }
//}
//class Solution {
//    private String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
//    private String[] names = {"黑桃", "红心", "梅花", "方块"};
//    private List<Card> startCards;
//    private List<Card> endCards;
//    private List<Player> players;
//
//    public Solution(){
//        this.startCards = new ArrayList<>();
//        this.endCards = new ArrayList<>();
//        this.players = new ArrayList<>();
//    }
//    public void createCard(){
//        for (String name : names){
//            for(String num : nums){
//                startCards.add(new Card(num, name));
//            }
//        }
//    }
//    // 洗牌
//    public void refreshCard(){
//        Random random = new Random();
//        Integer size = startCards.size();
//        for(int i=0; i<size; i++){
//            Card card = new Card();
//            do{
//                card = startCards.get(random.nextInt(size));
//            }while (endCards.contains(card));
//            endCards.add(card);
//        }
//    }
//    // 创建角色
//    public void createPlayers(){
//        Integer num = 2;
//        for(int i=0; i<num; i++){
//            players.add(new Player(getId(), getName()));
//        }
//    }
//    private Integer getId(){
//        Scanner input = new Scanner(System.in);
//        Integer id;
//        while (true){
//            try {
//                System.out.print("输入ID:");
//                id = input.nextInt();
//                break;
//            }catch (Exception e){
//                System.out.print("请重新输入");
//                input = new Scanner(System.in);
//            }
//        }
//        return id;
//    }
//    private String getName(){
//        System.out.print("输入姓名:");
//        Scanner input = new Scanner(System.in);
//        return input.next();
//    }
//    // 给角色发牌
//    public void sendCard(){
//        int num = 3, index = 0;
//        for (int i=0;i<num;i++){
//            for(int j=0;j<players.size();j++){
//                Player player = players.get(j);
//                player.cards.add(endCards.get(index++));
//            }
//        }
//    }
//    // 开始游戏
//    public void startGame(){
//        Card maxCard = new Card("0", "方块");
//        Player winner = new Player();
//        for (int i=0;i<players.size();i++){
//            Player player = players.get(i);
//            List<Card> cardList = player.cards;
//            Collections.sort(cardList);
//            Card card = cardList.get(cardList.size() - 1);
//            if(maxCard.compareTo(card) < 0){
//                maxCard = card;
//                winner = player;
//
//            }
//            System.out.println("玩家:"+ player.getName() + "最大的牌为:" + card.getName()+ card.getNum());
//        }
//        System.out.println("玩家:"+winner.getName()+"获胜");
//        System.out.println("玩家各自的手牌为:");
//        for(int j=0;j<players.size();j++){
//            Player player = players.get(j);
//            System.out.print("玩家 :" + player.getName()+"[");
//            for(int i=0;i<player.cards.size();i++){
//                if(i!=0) System.out.print(",");
//                System.out.print(player.cards.get(i).getName()+
//                        player.cards.get(i).getNum());
//            }
//            System.out.print("]\n");
//        }
//    }
//}
///**
// * 扑克牌类
// */
//class Card implements Comparable<Card> {
//    private String num;
//    private String name;
//    public Card(){
//    }
//    public Card(String num, String name){
//        this.num = num;
//        this.name = name;
//    }
//
//    public String getNum() {
//        return num;
//    }
//
//    public void setNum(String num) {
//        this.num = num;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public int compareTo(Card card) {
//        int numCompare = getNumValue(this.num).compareTo(getNumValue(card.num));
//        if(numCompare == 0){
//            return getNameValue(this.name).compareTo(getNameValue(card.name));
//        }
//        return numCompare;
//    }
//
//
//    private Integer getNumValue(String num){
//        switch (num){
//            case "A": return 12;
//            case "2": return 13;
//            case "3": return 1;
//            case "4": return 2;
//            case "5": return 3;
//            case "6": return 4;
//            case "7": return 5;
//            case "8": return 6;
//            case "9": return 7;
//            case "10": return 8;
//            case "J": return 9;
//            case "Q": return 10;
//            case "K": return 11;
//        }
//        return -1;
//    }
//    // 获取花色大小
//    private Integer getNameValue(String name){
//        switch(name){
//            case "黑桃": return 4;
//            case "红心": return 3;
//            case "梅花": return 2;
//            case "方块": return 1;
//        }
//        return -1;
//    }
//}
//
///**
// * 玩家类
// */
//class Player{
//    private Integer id;
//    private String name;
//    List<Card> cards;
//    public Player(){
//
//    }
//    public Player(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//        this.cards = new ArrayList<Card>();
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
