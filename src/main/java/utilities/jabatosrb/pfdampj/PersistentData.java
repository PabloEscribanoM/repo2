package jabatosrb.pfdampj;

import jabatosrb.pfdamapj.*;
import javafx.scene.paint.Material;

public class PersistentData {

    private static String encryptKey;
    private static Administrador user;
    private static Socio socioMod;
    private static SocioJugador socioJugadorMod;
    private static Escuela escuelaMod;
    private static Administrador adminMod;
    private static Materiales materialesMod;
    private static Patrocinador patrocinadorMod;
    private static Entrenadores entrenadorMod;
    private static FuturoSocio futuroSocioMod;
    private static Club club;

    public static SocioJugador getSocioJugadorMod() {
        return socioJugadorMod;
    }

    public static void setSocioJugadorMod(SocioJugador socioJugadorMod) {
        PersistentData.socioJugadorMod = socioJugadorMod;
    }

    public static String getEncryptKey() {
        return encryptKey;
    }

    public static void setEncryptKey(String encryptKey) {
        PersistentData.encryptKey = encryptKey;
    }

    public static Administrador getUser() {
        return user;
    }

    public static void setUser(Administrador user) {
        PersistentData.user = user;
    }

    public static Socio getSocioMod() {
        return socioMod;
    }

    public static void setSocioMod(Socio socioMod) {
        PersistentData.socioMod = socioMod;
    }

    public static Escuela getEscuelaMod() {
        return escuelaMod;
    }

    public static void setEscuelaMod(Escuela escuelaMod) {
        PersistentData.escuelaMod = escuelaMod;
    }

    public static Administrador getAdminMod() {
        return adminMod;
    }

    public static void setAdminMod(Administrador adminMod) {
        PersistentData.adminMod = adminMod;
    }

    public static Materiales getMaterialesMod() {
        return materialesMod;
    }

    public static void setMaterialesMod(Materiales materialesMod) {
        PersistentData.materialesMod = materialesMod;
    }

    public static Patrocinador getPatrocinadorMod() {
        return patrocinadorMod;
    }

    public static void setPatrocinadorMod(Patrocinador patrocinadorMod) {
        PersistentData.patrocinadorMod = patrocinadorMod;
    }

    public static Entrenadores getEntrenadorMod() {
        return entrenadorMod;
    }

    public static void setEntrenadorMod(Entrenadores entrenadorMod) {
        PersistentData.entrenadorMod = entrenadorMod;
    }

    public static FuturoSocio getFuturoSocioMod() {
        return futuroSocioMod;
    }

    public static void setFuturoSocioMod(FuturoSocio futuroSocioMod) {
        PersistentData.futuroSocioMod = futuroSocioMod;
    }

    public static Club getClub() {
        return club;
    }

    public static void setClub(Club club) {
        PersistentData.club = club;
    }
}
