package imd.ufrn.br.purposesong.use_case;

import imd.ufrn.br.purposesong.database.PlaylistRepository;
import imd.ufrn.br.purposesong.entity.Playlist;

public class AddPlaylist {
    final PlaylistRepository playlistRepository;

    public AddPlaylist(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void execute(Playlist playlist) {
        this.playlistRepository.create(playlist);
    }
}
