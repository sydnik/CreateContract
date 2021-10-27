package org.sydnik.createContract.myComponent;

public enum ValueButton {
    VIEW_MAIN_PAGE("Главная страница"),
    VIEW_SETTINGS_MANAGER("Настройки менеджера"),
    VIEW_SELECT_CLIENT("Выбрать клиента"),
    VIEW_CLIENT("Выбрать"),
    VIEW_BASIC_CONTRACT("Изменить"),
    VIEW_BACK_SELECT_CLIENT("Назад"),
    VIEW_DATA_ABOUT_CLIENT("Изменить"),
    VIEW_NEW_CLIENT("Добавить нового клиента"),
    VIEW_UP_SALE_CONTRACT("Изменить"),
    VIEW_SUPPLEMENTARY_AGREEMENT_UP_SALE("Изменить"),
    VIEW_ADD_NEW_DECOR("Добавить декор"),
    VIEW_CREATE_FILE_FOR_CUTTING("Создать"),
    VIEW_DATA_FOR_VIBER("Данные для вайбер"),
    VIEW_SUM_IN_BYN_TODAY("Суммы в рублях на сегодня"),
    VIEW_SELECT_PATH("Указать путь"),
    VIEW_INVOICE_DOCUMENT("Изменить"),
    VIEW_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT("Изменить"),

    SAVE_SETTING_MANAGER("Сохранить"),
    SAVE_NEW_DATA_CLIENT("Добавить нового клиента"),
    SAVE_DATA_ABOUT_CLIENT("Сохранить"),
    SAVE_DATA_BASE_CONTRACT_CLIENT("Сохранить"),
    SAVE_DATA_UP_SALE_CONTACT("Сохранить"),
    SAVE_DATA_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT("Сохранить"),
    SAVE_CURRENCY("Сохранить курс"),
    SAVE_DECOR("Добавить декор"),
    SAVE_DATA_INVOICE_DOCUMENT("Сохранить"),
    SAVE_DATA_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTACT("Сохранить"),
    SAVE_FILE_FOR_CUTTING("Создать файл для распила"),

    OPEN_DIRECTORY_WITH_FILE("Открыть папку с файлом"),
    OPEN_FILE_BASIC_CONTRACT("Открыть файл"),
    OPEN_FILE_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT("Открыть файл"),
    OPEN_FILE_UP_SALE_CONTRACT("Открыть файл"),
    OPEN_FILE_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTRACT("Открыть файл"),
    OPEN_FILE_INVOICE_DOCUMENT("Открыть файл"),

    PRINT_UP_SALE_CONTRACT("Распечатать 2 раза"),
    PRINT_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT("Распечатать 2 раза"),
    PRINT_BASE_CONTRACT("Распечатать 2 раза"),
    PRINT_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTRACT("Распечатать 2 раза"),
    PRINT_INVOICE_DOCUMENT("Распечатать"),

    ORDER_UP_SALE_MAUNFELD("Скопировать Maunfeld"),
    ORDER_UP_SALE_SINK("Скопировать Emar"),
    ORDER_SUPPLEMENTARY_AGREEMENT_UP_SALE_SINK("Скопировать Maunfeld"),
    ORDER_SUPPLEMENTARY_AGREEMENT_UP_SALE_MAUNFELD("Скопировать Emar"),

    SELECT_PAYMENT_CASH("Наличные"),
    SELECT_PAYMENT_CARD("Карта"),
    SELECT_PAYMENT_NON_CASH("Безнал"),

    COPY_PREPAYMENT("Скопировать \"Предоплата\""),
    COPY_PAY_TO_50_PERCENT("Скопировать \"Оплата до 50%\""),
    COPY_PAY_TO_100_PERCENT("Скопировать \"Оплата до 100%\""),

    SEARCH_CLIENT_BUTTON("Найти");


    private String name;

    ValueButton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
