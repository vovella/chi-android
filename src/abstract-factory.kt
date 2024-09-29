interface AbstractFactory{
    fun createUserNotification(): AbstractUserNotification;
    fun createAdminNotification():AbstractAdminNotification;
}

interface AbstractNotification{
    fun getNotification(msg: String): String;
}

class AbstractUserNotification: AbstractNotification{
    override fun getNotification(msg: String): String{
        return "User notification: ${msg}";
    }
}

class AbstractAdminNotification: AbstractNotification{
    override fun getNotification(msg: String): String{
        return "Admin notification: ${msg}. IP: 127.0.0.1. ";
    }
}


class TextNotificationFactory: AbstractFactory{
    override fun createUserNotification(): AbstractUserNotification{
        return AbstractUserNotification();
    }
    override fun createAdminNotification(): AbstractAdminNotification{
        return AbstractAdminNotification();
    }
}

fun main(){
    var factory = TextNotificationFactory();
    val userNotifacation = factory.createUserNotification();
    println(userNotifacation.getNotification("Security alert"));

    val adminNotifacation = factory.createAdminNotification();
    println(adminNotifacation.getNotification("Security alert"));
}