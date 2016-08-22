package com.vova_cons.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParser {
    /**
     * Читает данные для возвращаемого объекта из XML файла.
     *
     * @param parsedClass класс считываемого объекта
     * @param file        файл откуда происходит чтение
     * @return объект считанный их файла
     */
    public static Object parseFromFile(Class parsedClass, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(parsedClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    /**
     * Записывает объект в файл
     *
     * @param parsedClass класс записываемого класса
     * @param object      записываемый объект
     * @param file        файл куда происходит запись
     */
    public static void saveToFile(Class parsedClass, Object object, File file) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(parsedClass);
        Marshaller mar = jaxbContext.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        mar.marshal(object, file);
    }
}