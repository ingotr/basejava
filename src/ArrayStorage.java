import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private int size = 0;

    void clear() {
        for (Resume resume : storage) {
            if (resume != null) {
                resume = null;
            }
        }
        size = 0;
        System.out.println("Хранилище очищено");
    }

    void save(Resume r) {
        if (r == null) {
            return;
        }

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                return;
            }
        }
    }

    Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            System.out.format("Резюме с uuid: %s удалено%n", uuid);
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            storage[size - 1] = null;
            size--;
            removeDuplicates();
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        System.out.format("Резюме с таким uuid: %s нет в хранилище\n", uuid);
        return -1;
    }

    private void removeDuplicates() {
        for (int j = 0; j < size; j++) {
            for (int k = j + 1; k < size - 1; k++) {
                if (storage[j] == null) {
                    continue;
                }
                if (storage[j].equals(storage[k])) {
                    storage[k] = null;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        return size;
    }
}
