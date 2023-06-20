package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CsvUserRepositoryImpl implements UserRepository {
    private static final String CSV_FILE_PATH = "users.csv";
    private static final String[] CSV_HEADER = { "id","name","email","password","access" };

    private static final CsvUtil csvOperator = new CsvUtil(CSV_FILE_PATH, CSV_HEADER);

    @Override
    public Optional<User> findById(UUID id) {
        List<User> users = readCsvFile();
        return users.stream()
                .filter(user -> user.getId().equals(Optional.of(id)))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return readCsvFile();
    }

    @Override
    public void update(UUID id, User value) {
        List<User> users = readCsvFile();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(Optional.of(id))) {
                users.set(i, value);
                break;
            }
        }
        writeCsvFile(users);
    }

    @Override
    public User create(User value) {
        ArrayList<User> users = new ArrayList(readCsvFile());
        value.setId(UUID.randomUUID());
        users.add(value);
        writeCsvFile(users);
        return value;
    }

    @Override
    public void delete(UUID id) {
        ArrayList<User> users = new ArrayList(readCsvFile());
        users.removeIf(user -> user.getId().equals(Optional.of(id)));
        writeCsvFile(users);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        List<User> users = readCsvFile();
        return users.stream()
                .filter(user -> user.getName().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> login(String email, String password) {
        List<User> users = readCsvFile();
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public int quantityORegisterUsers() {
        List<User> users = readCsvFile();
        return users.size();
    }

    private List<User> readCsvFile() {
        List<String[]> lines = csvOperator.readCsvFile();

        return lines.stream().map(fields -> {
            User user = new User();
            user.setId(UUID.fromString(fields[0]));
            user.setName(fields[1]);
            user.setEmail(fields[2]);
            user.setPassword(fields[3]);

            switch (fields[4]) {
                case "VIP": user.setVipUser();
                case "NORMAL": user.setNormalUser();
            }

            return user;
        }).toList();
    }

    private void writeCsvFile(List<User> users) {
        // HEADER = { "id","name","email","password","access" };
        List<String[]> lines = users.stream().map(it -> {
            String[] line = {
                    it.getId().get().toString(),
                    it.getName(),
                    it.getEmail(),
                    it.getPassword(),
                    it.getPassword()
            };
            return line;
        }).toList();

        csvOperator.writeCsvFile(lines);
    }

    // Singleton ---------------------
    private static final CsvUserRepositoryImpl instance = new CsvUserRepositoryImpl();
    private CsvUserRepositoryImpl() {}
    public static CsvUserRepositoryImpl getInstance() {
        return CsvUserRepositoryImpl.instance;
    }
}
