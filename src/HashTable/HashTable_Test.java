//package HashTable;
import java.util.LinkedList;

// HashTable 클래스
class HashTable {

    // 데이터를 저장할 리스트를 배열로 선언 -> 배열에 저장될 데이터의 타입을 LinkedList로 만든다.
    LinkedList<Node>[] table;

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
    }

    // 해시 테이블을 만드는 순간 배열 사이즈를 얼마만큼 할건지 미리 선언
    public HashTable(int size) {
        table = new LinkedList[size];
    }

    // getHashCode(): 키를 받아서 해시코드를 반환한다.
    int getHashCode(String key) {
        int hashCode = 0;

        //키 값의 유니코드를 가져와서 덧셈하는 해시 알고리즘
        for (char c : key.toCharArray()) { hashCode += c; }

        return hashCode;
    }// getHashCode 종료

    //getIndex() : hashCode를 받아서 배열의 index를 반환하는 함수
    public int getIndex(int hashCode) {
        return (hashCode % table.length);
    }

    Node searchNode(int index, String key) {
        LinkedList<Node> indexedList = table[index];

        for (Node n : indexedList) {
            if (n.key == key) { return n; }
        }
        return null;
    }

    //printAllKeyValue() : index를 받아 해당되는 연결리스트의 모든 key값과 value값을 출력하는 함수
    void printAllKeyValue (int index) {
        LinkedList<Node> indexedList = table[index];
        if (indexedList == null) return;
        System.out.println("----인덱스" + index +"에 해당하는 연결리스트의 모든 key, value 값----");
        for (Node node : indexedList) {
            if(node == null) return;
            System.out.println("key : " + node.key + "  //  value : " + node.value);
        }
    }

    // put(): key와 value를 받아서 데이터를 저장하는 함수
    public void put(String key, String value) {
        //해시코드
        int hashCode = getHashCode(key);
        //배열 인덱스 번호
        int index = getIndex(hashCode);

        //배열에 들어갔다는 확인을 위한 출력
        System.out.println(key +", hashcode("+ hashCode +"), index(" + index+")");

        //해당 인덱스 버킷에 아무것도 있지 않을 때
        if (table[index] == null) {
            table[index] = new LinkedList<Node>();
            table[index].add(new Node(key, value));
        }
        else {
            Node searched = searchNode(index, key);
            if (searched != null) { //같은 key값이면 중단
                System.out.println("다른 key값을 입력하여주세요");
                return;
            }
            else { table[index].add(new Node(key, value)); }    //같은 key값이 없으면 value값 추가해주기
        }
    }

    public void update(String key, String value){
        //해시코드
        int hashCode = getHashCode(key);
        //배열 인덱스 번호
        int index = getIndex(hashCode);
        if (table[index] == null) {
            System.out.println("해당 인덱스 버킷에 아무것도 있지 않습니다.");
            return;
        }

        Node searched = searchNode(index, key);
        if (searched != null) { searched.value = value; }
    }

    // get(): key를 받아서 value를 반환하는 함수
    public String get(String key) {
        int hashCode = getHashCode(key);
        int index = getIndex(hashCode);

        Node searched = searchNode(index, key);

        if (searched == null) { return "Not Found"; }
        else { return searched.value; }
    }

    public void deleteNode(String key){
        int hashCode = getHashCode(key);
        int index = getIndex(hashCode);
        Node node = searchNode(index, key);
        if(node==null) System.out.println("Not found");
        else {
            table[index].remove(node);
        }
    }
}


public class HashTable_Test {

    public static void main(String[] args) {
        HashTable h = new HashTable(3);

        h.put("key1", "value1"); // key와 value를 받아 배열에 데이터를 저장
        h.put("키2", "값2");
        h.put("key키3", "value값3");

        // 덮어쓰기 -> 이제 update 메소드를 이용하여야 함.
        h.put("key1", "value1->reValue");

        //덮어쓰기
        h.update("key키3", "zz");

        System.out.println("===== 존재하는 데이터 호출 =====");
        System.out.println(h.get("key1")); // key의 value를 반환
        System.out.println(h.get("키2"));
        System.out.println(h.get("key키3"));

        System.out.println("===== 없는 데이터 호출 =====");
        System.out.println(h.get("keyX"));

        System.out.println("===== 각 인덱스의 연결리스트에 든 모든 key값과 value값 출력 =====");
        h.printAllKeyValue(0);
        h.printAllKeyValue(1);
        h.printAllKeyValue(2);

        h.deleteNode("키2");

        System.out.println("===== h.deleteNode(\"키2\"); 후 모든 key값과 value값 출력 =====");
        h.printAllKeyValue(0);
        h.printAllKeyValue(1);
        h.printAllKeyValue(2);
    }

}