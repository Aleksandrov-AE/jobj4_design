package ru.job4j.ood.lsp.example;


public class Isp {

 //интерфейс заставляет реализовыватьесть и спать
    interface Worker {
        void work();
        void eat();  //?
        void sleep(); //?  Он должен только работать
    }

    // будет использоваться только один
    interface Notifier {
        void sendEmail(String to, String subject, String body);
        void sendSms(String phone, String text);
        void sendPush(String device, String title, String body);

    }

    // один интерфейс на все случаи
    interface Repository<T> {
        T save(T entity);
        T findById(long id);
        void deleteById(long id);
        void commit();
        void rollback();
    }

}
