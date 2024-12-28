package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

public class JamesCLIHelper extends HelperBase{

    public JamesCLIHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password){
        CommandLine cmd = new CommandLine(
                "java","-cp","\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "AddUser", email,password);
        cmd.setWorkingDirectory(manager.property("james.workingDir"));//установить рабочую директорию:значение перенесли в пропертис
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);//перехватываем сообщение об ошибке, чтобы понять почему пользователь НЕ добавлен
        cmd.execute();//запустить команду
        cmd.waitFor();//подождать пока она доработает до конца
        System.out.println(out);//печатаем на консоль то, что перехватили
    }
}
