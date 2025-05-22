package com.tildawn.Enums;

public enum music {
    MUSIC_0("Stop the music",0),
    MUSIC_1("musics/1.mp3", 1),
    MUSIC_2("musics/2.mp3", 2),
    MUSIC_3("musics/3.mp3", 3),
    MUSIC_4("musics/4.mp3", 4),
    MUSIC_5("musics/5.mp3", 5),
    MUSIC_6("musics/6.mp3", 6),
    MUSIC_7("musics/7.mp3", 7),
    MUSIC_8("musics/8.mp3", 8),
    MUSIC_9("musics/9.mp3", 9);

    private final String path;
    private final int index;

    music(String path, int index) {
        this.path = path;
        this.index = index;
    }

    public String getPath() {
        return path.trim();
    }

    public int getIndex() {
        return index;
    }

    public static music fromIndex(int index) {
        for (com.tildawn.Enums.music music : values()) {
            if (music.index == index) {
                return music;
            }
        }
        return null;
    }
}
