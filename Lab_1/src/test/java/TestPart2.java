import org.example.Part2.BinomialHeap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPart2 {

    // Black box testing

    @Test
    @DisplayName("Test correct extracting")
    void testCorrectExtracting() {
        BinomialHeap binHeap = new BinomialHeap();

        for (int i = 100; i >= 65; i--) {
            binHeap.insert(i);
        }
        binHeap.insert(10);
        binHeap.extractMin();

        assertEquals(65, binHeap.findMinimum());
    }

    @Test
    @DisplayName("Test delete")
    void testDelete() {
        BinomialHeap binHeap = new BinomialHeap();

        for (int i = 100; i >= 65; i--) {
            binHeap.insert(i);
        }
        binHeap.delete(65);
        assertEquals(66, binHeap.findMinimum());
    }

    @Test
    @DisplayName("Test clear heap")
    void testClearHeap() {
        BinomialHeap binHeap = new BinomialHeap();

        for (int i = 100; i >= 65; i--) {
            binHeap.insert(i);
        }
        binHeap.makeEmpty();
        assertEquals(0, binHeap.getSize());
    }

    @Test
    @DisplayName("Test empty")
    void testEmpty() {
        BinomialHeap binHeap = new BinomialHeap();
        assertTrue(binHeap.isEmpty());
    }

    @Test
    @DisplayName("Test not empty")
    void testNotEmpty() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(12);
        assertFalse(binHeap.isEmpty());
    }

    // Gray box testing

    @Test
    @DisplayName("Test one element heap")
    void testLittleHeap() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(12);
        assertEquals(12, binHeap.findMinimum());
    }

    @Test
    @DisplayName("Test extract last")
    void testExtreactLast() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(12);
        binHeap.extractMin();
        assertEquals(0, binHeap.getSize());
    }

    @Test
    @DisplayName("Test merge decreasing")
    void testMergeDecreasing() {
        BinomialHeap binHeap = new BinomialHeap();

        for (int i = 100; i >= 65; i--) {
            binHeap.insert(i);
        }
        binHeap.insert(10);

        assertEquals(10, binHeap.findMinimum());
    }

    @Test
    @DisplayName("Test merge increasing")
    void testMergeIncreasing() {
        BinomialHeap binHeap = new BinomialHeap();

        for (int i = 65; i <= 100; i++) {
            binHeap.insert(i);
        }
        binHeap.insert(110);

        assertEquals(65, binHeap.findMinimum());
    }

    @Test
    @DisplayName("Test delete not existing")
    void testDeleteNotExisting() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(10);
        binHeap.delete(0);
        assertEquals(1, binHeap.getSize());
    }

    @Test
    @DisplayName("Test delete last")
    void testDeleteLast() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(12);
        binHeap.delete(12);
        assertEquals(0, binHeap.getSize());
    }

    @Test
    @DisplayName("Test extract from empty heap")
    void testExtractFromEmptyHeap() {
        BinomialHeap binHeap = new BinomialHeap();
        assertEquals(-1, binHeap.extractMin());
    }

    @Test
    @DisplayName("Test delete from empty heap")
    void testDeleteFromEmptyHeap() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.delete(0);
        assertEquals(0, binHeap.getSize());
    }

    // White box testing
    @Test
    @DisplayName("Test insert negative")
    void testInsertNegative() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(-10);
        assertTrue(binHeap.isEmpty());
    }

    @Test
    @DisplayName("Test decrease many keys")
    void testDecreaseKeyValueMany() {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(13);
        heap.insert(12);
        heap.insert(11);
        heap.insert(10);
        heap.insert(9);
        heap.insert(8);
        heap.insert(7);
        heap.insert(6);
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);

        for (int i = 1; i <= 13; i++) {
            heap.decreaseKeyValue(i, i-1);
        }

        assertEquals(0, heap.findMinimum());
    }

    @Test
    @DisplayName("Test decrease many keys inverted")
    void testDecreaseKeyManyInverted() {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(13);
        heap.insert(12);
        heap.insert(11);
        heap.insert(10);
        heap.insert(9);
        heap.insert(8);
        heap.insert(7);
        heap.insert(6);
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);

        for (int i = 13; i >= 1; i--) {
            heap.decreaseKeyValue(i, i-1);
        }

        assertEquals(0, heap.findMinimum());
    }

    @Test
    @DisplayName("Test decrease key triggers while()")
    void testDecreaseKeyValueTriggersWhile() {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);

        heap.decreaseKeyValue(40, 5);

        assertEquals(5, heap.findMinimum());
    }

    @Test
    void testExtractMinTriggersWhile() {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(10);
        heap.insert(5);
        heap.insert(20);

        int min = heap.extractMin();

        assertEquals(5, min);
    }

    @Test
    @DisplayName("Test decrease not existing")
    void testDecreaseNotExisting() {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);

        heap.decreaseKeyValue(199, 5);

        assertEquals(10, heap.findMinimum());
    }
}