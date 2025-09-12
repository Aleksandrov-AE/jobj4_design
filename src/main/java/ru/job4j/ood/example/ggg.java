package ru.job4j.ood.example;

class Report {
    private String text;

    public Report(String text) {
        this.text = text;
    }

    // Нарушение: класс и хранит данные отчёта, и умеет выводить их в разных форматах
    public void printPdf() {
        System.out.println("PDF: " + text);
    }
    public void printHtml() {
        System.out.println("<html>" + text + "</html>");
    }
}

