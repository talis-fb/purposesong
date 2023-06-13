package imd.ufrn.br.purposesong.player;

import imd.ufrn.br.purposesong.entity.Song;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.util.Optional;

public class SongPlayer {
    private Optional<Player> player;
    private Optional<Song> currentSong = Optional.empty();
    private long currentSongTimeSeconds = 0;
    private long songDurationSeconds = 0;

    public void play(Song song) {
        this.currentSong = Optional.of(song);
        this.resume();
    }

    public void resume() {
        if (this.currentSong.isEmpty()) {
            return;
        }

        var song = this.currentSong.get();
        System.out.println("[PLAYER] Musica iniciada " + song.name);

        try {
            var fileInputStream = new FileInputStream(song.path);
            var player = new Player(fileInputStream);
            this.player = Optional.of(player);

            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        System.out.println("[PLAYER] Musica pausando");
        if (player.isPresent()) {
            try {
                player.get().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Singleton ---------
    private static final SongPlayer instance = new SongPlayer();
    public static SongPlayer getInstance() {
        return SongPlayer.instance;
    }
}
