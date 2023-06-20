package imd.ufrn.br.purposesong.database.csv;

import imd.ufrn.br.purposesong.database.UserRepository;
import imd.ufrn.br.purposesong.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CsvUserRepositoryImpl
        extends CsvAbstractRepositoryImpl<User>
        implements UserRepository
{
    private static final String CSV_FILE_PATH = "users.csv";
    private static final String[] CSV_HEADER = { "id","name","email","password","access" };

    private static final CsvOperator csvOperator = new CsvOperator(CSV_FILE_PATH, CSV_HEADER);

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

    List<User> readCsvFile() {
        List<String[]> lines = csvOperator.readCsvFile();

        return lines.stream().map(fields -> {
            User user = new User();
            user.setId(UUID.fromString(fields[0]));
            user.setName(fields[1]);
            user.setEmail(fields[2]);
            user.setPassword(fields[3]);

            if (fields[4].equals("VIP"))
                user.setVipUser();
            if (fields[4].equals("NORMAL"))
                user.setNormalUser();

            return user;
        }).toList();
    }

    void writeCsvFile(List<User> users) {
        // HEADER = { "id","name","email","password","access" };
        List<String[]> lines = users.stream().map(it -> {
            String[] line = {
                    it.getId().get().toString(),
                    it.getName(),
                    it.getEmail(),
                    it.getPassword(),
                    it.isVipUser() ? "VIP" : "NORMAL"
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
