        import java.io.BufferedReader;
        import java.util.*;
        import java.io.*;
        import java.sql.*;
        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.border.LineBorder;
        import javax.swing.plaf.BorderUIResource;
        import java.awt.*;
        import java.awt.event.*;
        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
/**
 * Created by spbw0-rep6 on 05.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        DetailContact book = new DetailContact();
        Gson gson = new Gson();
        String json = gson.toJson(book);
        ArrayList<DetailContact> contacts = new ArrayList<>();
        File inputFile = new File("contacts.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Comparator<Contact> compByNameAscending = (name1, name2) -> name1.getName().compareTo(name2.getName());
        Comparator<Contact> compByNameDescending = (name1, name2) -> name2.getName().compareTo(name1.getName());
        Comparator<Contact> compByEmailAscending = (email1, email2) -> email1.getEmail().compareTo(email2.getEmail());
        Comparator<Contact> compByEmailDescending = (email1, email2) -> email2.getEmail().compareTo(email1.getEmail());
        JFrame frame = new JFrame("Телефонная книга");
        frame.setSize(400, 300);
        JPanel panel = new JPanel();
        JLabel l1 = new JLabel("Меню:");
        l1.setLocation(20, 5);
        JButton b1 = new JButton("1. Добавить контакт");
        JButton b2 = new JButton("2. Показать все контакты");
        JButton b3 = new JButton("3. Выход");
        JButton b4 = new JButton("4. Удалить контакт");
        JButton b5 = new JButton("5. Настройка сортировки контактов");
        panel.add(l1);
        l1.setSize(150, 30);
        b1.setSize(150, 30);
        b2.setSize(185, 30);
        b3.setSize(90, 30);
        b4.setSize(150, 30);
        b5.setSize(240, 30);
        b1.setLocation(20, 30);
        b2.setLocation(20, 70);
        b3.setLocation(20, 110);
        b4.setLocation(20, 150);
        b5.setLocation(20, 190);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.setBorder(new LineBorder(Color.BLUE, 2));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.setLayout(null);
        frame.setResizable(false);
        frame.setContentPane(panel);
        b1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                PrintWriter writer = new PrintWriter(new FileOutputStream(inputFile, true));
                String name = JOptionPane.showInputDialog(frame, "Введите имя:", "Введите значение", JOptionPane.WARNING_MESSAGE);
                book.setName(name);
                while (true) {
                String phone = JOptionPane.showInputDialog(frame, "Введите  телефон или нажмите 'n' для выхода:", "Введите значение", JOptionPane.WARNING_MESSAGE);
                if (phone.equals("n"))
                break;
                else
                book.setPhone(phone);
                }
                String email = JOptionPane.showInputDialog(null, "Введите email:", "Введите значение", JOptionPane.WARNING_MESSAGE);
                book.setEmail(email);
                String address = JOptionPane.showInputDialog(null, "Введите адрес:", "Введите значение", JOptionPane.WARNING_MESSAGE);
                book.setAddress(address);
                String workplace = JOptionPane.showInputDialog(null, "Введите место работы:", "Введите значение", JOptionPane.WARNING_MESSAGE);
                book.setWorkplace(workplace);
                contacts.add(book);
                for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getPhone().size() == 1) {
                writer.print(contacts.get(i).getName() + ", " + contacts.get(i).getPhone() + ", " + contacts.get(i).getEmail() + ", " + contacts.get(i).getAddress() + ", " + contacts.get(i).getWorkplace() + "\n");
                writer.flush();
                contacts.get(i).getPhone().clear();
                } else {
                writer.print(contacts.get(i).getName() + ", ");
                for (int k = 0; k < contacts.get(i).getPhone().size(); k++) {
                writer.print(contacts.get(i).getPhone().get(k) + " ");
                }
                writer.print(", " + contacts.get(i).getEmail() + ", " + contacts.get(i).getAddress() + ", " + contacts.get(i).getWorkplace());
                writer.println();
                writer.flush();
                contacts.get(i).getPhone().clear();
                }
                }
                contacts.clear();
                writer.close();

                writeSerialize(book);

                FileWriter gsonWriter = new FileWriter("gson.json", true);

                gsonWriter.write(json);
                gsonWriter.close();

                } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Отмена");
                } catch (NullPointerException nx) {
                JOptionPane.showMessageDialog(null, "Отмена");
                }
                }
                });
                b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                String line, s;
                line = "";
                BufferedReader readerFromFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
                while ((s = readerFromFile.readLine()) != null) {
                line += s + '\n';
                }
                JOptionPane.showMessageDialog(null, line, "Контакты из readerFromFile", JOptionPane.PLAIN_MESSAGE);
                readerFromFile.close();

                JOptionPane.showMessageDialog(null, readSerialize().getName() + ", " + readSerialize().getAddress(), "Контакты из Serialize", JOptionPane.PLAIN_MESSAGE);
                DetailContact newBook = gson.fromJson(new FileReader("gson.json"), DetailContact.class);
                JOptionPane.showMessageDialog(null, newBook.getName(), "Контакты из json", JOptionPane.PLAIN_MESSAGE);

                } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Отмена");
                } catch (ArrayIndexOutOfBoundsException ea) {
                JOptionPane.showMessageDialog(null, "Список контактов пустой");
                }
                }
                });
                b3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Выход из программы");
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
                });
                b4.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                String line;
                String s = "";
                BufferedReader readerFromFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
                while ((line = readerFromFile.readLine()) != null) {
                String[] information = line.split(", ");
                DetailContact book = new DetailContact();
                String name = information[0];
                s += name + "\n";
                String phone = information[1];
                String email = information[2];
                String address = information[3];
                String workplace = information[4];
                book.setName(name);
                book.setPhone(phone);
                book.setEmail(email);
                book.setAddress(address);
                book.setWorkplace(workplace);
                contacts.add(book);
                }
                readerFromFile.close();
                String input = JOptionPane.showInputDialog(null, "Введите имя для удаления:\n" + s, "Введите значение", JOptionPane.WARNING_MESSAGE);
                for (int i = 0; i < contacts.size(); i++) {
                if (input.equals(contacts.get(i).getName())) {
                contacts.remove(i);
                System.out.print(contacts.get(i).getName());
                }
                }
                PrintWriter writer = new PrintWriter(new FileOutputStream(inputFile));
                for (int i = 0; i < contacts.size(); i++) {
                writer.print(contacts.get(i).getName() + ", " + contacts.get(i).getPhone() + ", " +
                contacts.get(i).getEmail() + ", " + contacts.get(i).getAddress() + ", " + contacts.get(i).getWorkplace() + "\n");
                writer.flush();
                }
                writer.close();
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Отмена");

                } catch (ArrayIndexOutOfBoundsException eb) {
                JOptionPane.showMessageDialog(null, "Список контактов пустой");
                }
                }
                });
                b5.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                String line;
                String input;
                BufferedReader readerFromFile = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
                while ((line = readerFromFile.readLine()) != null) {
                String[] information = line.split(", ");
                DetailContact book = new DetailContact();
                String name = information[0];
                String phone = information[1];
                String email = information[2];
                String address = information[3];
                String workplace = information[4];
                book.setName(name);
                book.setPhone(phone);
                book.setEmail(email);
                book.setAddress(address);
                book.setWorkplace(workplace);
                contacts.add(book);
                }
                readerFromFile.close();
                input = JOptionPane.showInputDialog(null,
                "Введите \"ne\" для сортировки по имени, а при совпадении по email \n" +
                "Введите \"en\" для сортировки по email, а при совпадении по имени \n" +
                "Введите \"-ne\" для сортировки по имени в обратном порядке, а при совпадении по email \n" +
                "Введите \"-en\" для сортировки по email в обратном порядке, а при совпадении по имени \n" +
                "Введите \"n-e\" для сортировки по имени, а при совпадении по email в обратном порядке \n" +
                "Введите \"e-n\" для сортировки по email, а при совпадении по имени в обратном порядке \n" +
                "Введите \"-n-e\" для сортировки по имени в обратном порядке, а при совпадении по email в обратном порядке \n" +
                "Введите \"-e-n\" для сортировки по email в обратном порядке, а при совпадении по имени в обратном порядке",
                JOptionPane.WARNING_MESSAGE);
                if (input.equals("ne")) {
                Collections.sort(contacts, compByEmailAscending);
                Collections.sort(contacts, compByNameAscending);
                } else if (input.equals("en")) {
                Collections.sort(contacts, compByNameAscending);
                Collections.sort(contacts, compByEmailAscending);
                } else if (input.equals("-ne")) {
                Collections.sort(contacts, compByEmailAscending);
                Collections.sort(contacts, compByNameDescending);
                } else if (input.equals("-en")) {
                Collections.sort(contacts, compByNameAscending);
                Collections.sort(contacts, compByEmailDescending);
                } else if (input.equals("n-e")) {
                Collections.sort(contacts, compByEmailDescending);
                Collections.sort(contacts, compByNameAscending);
                } else if (input.equals("e-n")) {
                Collections.sort(contacts, compByNameDescending);
                Collections.sort(contacts, compByEmailAscending);
                } else if (input.equals("-n-e")) {
                Collections.sort(contacts, compByEmailDescending);
                Collections.sort(contacts, compByNameDescending);
                } else if (input.equals("-e-n")) {
                Collections.sort(contacts, compByNameDescending);
                Collections.sort(contacts, compByEmailDescending);
                } else
                JOptionPane.showMessageDialog(null, "Неверно введенная команда.");
                PrintWriter writer = new PrintWriter(new FileOutputStream(inputFile));
                for (int i = 0; i < contacts.size(); i++) {
                writer.print(contacts.get(i).getName() + ", " + contacts.get(i).getPhone() + ", " +
                contacts.get(i).getEmail() + ", " + contacts.get(i).getAddress() + ", " + contacts.get(i).getWorkplace() + "\n");
                writer.flush();
                contacts.get(i).getPhone().clear();
                }
                contacts.clear();
                writer.close();
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Список контактов пустой");
                }
                }
                });
                }
        public static void writeSerialize(Serializable object) {
                try {
                FileOutputStream fileOutputStream = new FileOutputStream("user1.data", true);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
                outputStream.writeObject(object);
                outputStream.flush();
                outputStream.close();
                } catch (FileNotFoundException e) {
                e.printStackTrace();
                } catch (IOException e) {
                e.printStackTrace();
                }
                }
        public static DetailContact readSerialize() {
                try {
                FileInputStream fileInputStream = new FileInputStream("user1.data");
                ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
                DetailContact cont = (DetailContact) inputStream.readObject();
                inputStream.close();
                return cont;
                } catch (FileNotFoundException e) {
                e.printStackTrace();
                } catch (IOException e) {
                e.printStackTrace();
                } catch (ClassNotFoundException e) {
                e.printStackTrace();
                }
                return null;
        }
}
