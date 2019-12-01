import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    short realLength = 0;

    void clear() {
        for (int i = 0; i < realLength; i++) {
            storage[i] = null;
        }
        realLength = 0;
    }

    void save(Resume r) {
            storage[realLength] = r;
            realLength++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < realLength; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int i;
        for (i = 0; i < realLength; i++) {
            if (storage[i].uuid.equals(uuid)) {
                realLength--;
                break;
            }
        }
        while (i <= realLength) {
            storage[i] = storage[i + 1];
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, realLength);
    }

    int size() {
        return realLength;
    }
}
