package datastruct;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class MyUnsortedListTest {

    @Test
    public void testSize(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testSize2(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        Assertions.assertTrue(list.size() == 0);
    }

    @Test
    public void testSomeAdd(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of(1, 2, 3, 4, 5, 6);
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3);
        list.append(4);
        list.append(5);
        list.append(6);
        Assertions.assertTrue(listRes.equals(list));
    }

    @Test
    public void testSomeRemove(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of(1, 2, 3);
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3, 4, 5, 6);
        list.remove(3);
        list.remove(3);
        list.remove(3);
        Assertions.assertTrue(listRes.equals(list));
    }

    @Test
    public void testManyAdd(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        for(int i = 0; i < 1000; i++){
            list.append(i);
        }
        Assertions.assertTrue(list.size() == 1000);
    }

    @Test
    public void testManyAddThenRemove(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        for(int i = 0; i < 1000; i++){
            list.append(i);
        }
        for(int i = 0; i < 1000; i++){
            list.remove(0);
        }
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testManyAddThenRemove2(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        for(int i = 0; i < 150; i++){ list.append(i); }
        for(int i = 0; i < 50; i++){ list.remove(0); }
        for(int i = 0; i < 200; i++){ list.append(i); }
        for(int i = 0; i < 300; i++){ list.append(i); }
        for(int i = 0; i < 200; i++){ list.remove(0); }
        for(int i = 0; i < 50; i++){ list.remove(0); }
        for(int i = 0; i < 20; i++){ list.append(i); }

        Assertions.assertTrue(list.size() == 370);
    }

    @Test
    public void testManyAddThenPopLast(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of(0);
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        for(int i = 0; i < 150; i++){ list.append(i); }
        for(int i = 0; i < 50; i++){ list.popLast(); }
        for(int i = 0; i < 200; i++){ list.append(i); }
        for(int i = 0; i < 300; i++){ list.append(i); }
        for(int i = 0; i < 200; i++){ list.popLast(); }
        for(int i = 0; i < 399; i++){ list.popLast(); }


        Assertions.assertTrue(listRes.equals(list));
    }

    @Test
    public void insertWrongPos(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        Assertions.assertThrows( IndexOutOfBoundsException.class, () -> {
            list.insert(12,5);
        });
    }

    @Test
    public void insertTest(){
        MyUnsortedList<Integer> list = MyUnsortedList.of(1,2,3,4);
        list.insert(12,2);
        Assertions.assertEquals(list.remove(2),12);
    }

    @Test
    public void insertThenRemoveTestSize(){
        MyUnsortedList<Integer> listCopy = MyUnsortedList.of(1,2,3,4);
        MyUnsortedList<Integer> list = MyUnsortedList.of(1,2,3,4);
        list.insert(12,2);
        list.remove(2);

        // BUG Trouvé : lorsque l'on supprime un élément de la liste, la taille de celle-ci n'est pas décrémentée
        Assertions.assertEquals(listCopy.size(), list.size());
    }


    @Test
    public void insertThenRemoveTest(){
        MyUnsortedList<Integer> listCopy = MyUnsortedList.of(1,2,3,4);
        MyUnsortedList<Integer> list = MyUnsortedList.of(1,2,3,4);
        list.insert(12,2);
        list.remove(2);

        // On peut se servir de la méthode toString qui nous montre que l'élément a bien été supprimé mais que l'indice n'a pas été décrémenté
        Assertions.assertTrue(list.equals(listCopy));
    }

    @Test
    void testBadRemove(){
        MyUnsortedList<Integer> list = MyUnsortedList.of(1, 2, 3,4);
        Assertions.assertThrows( IndexOutOfBoundsException.class, () -> {
            list.remove(0);
            list.remove(3);
        });
    }

    @Test
    void testPopOnEmptyList(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        Assertions.assertThrows( EmptyListException.class, () -> {
            list.pop();
        });
    }

    @Test
    void testPopLastOnEmptyList(){
        MyUnsortedList<Integer> list = MyUnsortedList.of();
        //Bug trouvé : mauvaise exception renvoyée (IndexOutOfBoundsException au lieu de EmptyListException dans la spécification)
        Assertions.assertThrows( EmptyListException.class, () -> {
            list.popLast();
        });
    }

    @Test
    void testPop(){
        MyUnsortedList<Integer> list = MyUnsortedList.of(1,2,3,4,5,6);
        Assertions.assertEquals(1, list.pop());
    }

    @Test
    void testPopLast(){
        MyUnsortedList<Integer> list = MyUnsortedList.of(1,2,3,4,5,6);
        Assertions.assertEquals(6, list.popLast());
    }

    @Test
    void testPrepend(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of(1,2,3,4,5,6);
        MyUnsortedList<Integer> list = MyUnsortedList.of(4,5,6);
        list.prepend(3);
        list.prepend(2);
        list.prepend(1);
        Assertions.assertTrue(listRes.equals(list));
    }

    @Test
    void testListDifferentSizes(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of(1,2);
        MyUnsortedList<Integer> list2 = MyUnsortedList.of(1,2,3);
        Assertions.assertFalse(listRes.equals(list2));

    }

    @Test
    void testEqualCoverage(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of(1,2);
        ArrayList<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        System.out.println(listRes.toString());
        Assertions.assertFalse(listRes.equals(l));
        Assertions.assertFalse(listRes.equals(null));
    }

    @Test
    void appendFirst(){
        MyUnsortedList<Integer> listRes = MyUnsortedList.of();
        listRes.append(1);
        listRes.append(2);
        listRes.append(3);


    }
}