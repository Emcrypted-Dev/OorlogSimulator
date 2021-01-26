package nl.emcrypted.oorlogsimulator.utils;

public enum Error {

    CONSOLE_ONLY(Color.translate("&cThis command can only be executed via the console.")),
    PLAYERS_ONLY(Color.translate("&cThis command can only be executed by players.")),
    PLAYER_NOT_FOUND(Color.translate("&cPlayer not found!")),
    REDIS_ERROR(Color.translate("&cThere is an error with the Redis configuration so this will not work!")),
    SQL_ERROR(Color.translate("&cThere is an error with the MySQL configuration so this will not work!")),
    MONGO_ERROR(Color.translate("&CThere is an error with the MongoDB configuration so this will not work!")),
    NO_PERMISSION(Color.translate("&cYou do not have the required authorization for this command!"));

    private String error;

    Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}