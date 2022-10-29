package HashTable;

import java.util.LinkedList;

// HashTable 클래스
class HashTable {

    // HashTable에 저장할 데이터를 담는다.
    class Node {
        // 검색어: 키
        String key;
        // 검색 결과: value값
        String value;

        // 생성자 노드 생성
        public Node(String key, String value) {
            super();
            this.key = key;
            this.value = value;
        }

        // value를 가져온다. = get 함수
        String value() {
            return value;
        }

        // value를 저장한다. = set 함수
        void value(String value) {
            this.value = value;
        }

    }// Node 종료

    // 데이터를 저장할 리스트를 배열로 선언 -> 배열에 저장될 데이터의 타입을 LinkedList로 만든다.
    LinkedList<Node>[] data;

    // 해시 테이블을 만드는 순간 배열 사이즈를 얼마만큼 할건지 미리 선언
    HashTable(int size) {
        this.data = new LinkedList[size];
    }

    // getHashCode(): 키를 받아서 해시코드를 반환한다.
    int getHashCode(String key) {
        int hashcode = 0;
        // 키 값의 아스키코드를 가져와서 덧셈하는 해시 알고리즘
        for(char c: key.toCharArray()) {
            hashcode += c;
        }
        return hashcode;

    }// getHashCode 종료

    //convertToIndex(): hashcode를 받아서 배열의 index를 받은 함수
    int convertToIndex(int hashcode) {

        return hashcode % data.length;

    }// convertToIndex 종료


    // <노드가 여러개 존재할 때>
    // searchKey(): 검색 시, index로 배열에서 찾은 이후에, 배열 안에 노드가 여러개 존재 할 때, 검색 키로 해당 노드를 찾아오는 함수
    Node searchkey(LinkedList<Node> list, String key) {
        //배열 방이 null일때
        if(list ==null) {
            return null;
        }
        //배열 방을 돈다.
        for(Node node: list) {
            //key값을 비교하면서 node를 찾는다.
            if(node.key.equals(key)) {
                return node;
            }

        }
        return null;
    }// searchkey 종료

    // put(): key와 value를 받아서 데이터를 저장하는 함수
    void put(String key, String value) {
        // 해시 코드
        int hashcode = getHashCode(key);
        // 배열 인덱스 번호
        int index = convertToIndex(hashcode);

        // 배열에 들어갔다는 확인을 위한 [출력]
        System.out.println(key +", hashcode("+ hashcode +"), index(" + index+")");

        // 배열 인덱스 번호를 이용해서 기존에 있는 데이터를 가져온다.
        LinkedList<Node> list = data[index];

        // 배열이 null일 경우
        if(list == null) {
            list= new LinkedList<Node>(); // new 리스트를 생성하고
            data[index] = list; // 리스트를
        }

        // 배열의 key로 searchKey하여 노드를 가져온다.
        Node node = searchkey(list, key);

        // 노드가 null이면 데이터가 없다는 뜻이다.
        if(node == null) {
            // 받아온 정보를 가지고 노드 객체를 생성한다.
            list.addLast(new Node(key, value));
        }else {

            // 노드가 null이 아닌 경우에는 해당 값으로 덮어쓰기해서 중복키를 처리
            node.value(value);

        }

    }// put 종료


    // get(): key를 받아서 value를 반환하는 함수
    String get(String key) {

        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        LinkedList<Node> list = data[index];

        Node node = searchkey(list, key);

        return node == null? "Not found": node.value(); // node가 null일 경우 Not found, null이 아닐 경우 node의 value를 반환

    } // get 종료

}// HashTable 종료

// Hash_Table_01 클래스
public class HashTable_Test {

    // main 메서드
    public static void main(String[] args) {
        HashTable h = new HashTable(3);

        h.put("key1", "value1"); // key와 value를 받아 배열에 데이터를 저장
        h.put("key2", "value2");
        h.put("key3", "value3");

        // 덮어쓰기
        h.put("key1", "value1->reValue");

        System.out.println(h.get("key1")); // key의 value를 반환
        System.out.println(h.get("key2"));
        System.out.println(h.get("key3"));

        // 없는 데이터 호출
        System.out.println(h.get("keyX"));

    }// main 종료

}// HashTable_Test 종료