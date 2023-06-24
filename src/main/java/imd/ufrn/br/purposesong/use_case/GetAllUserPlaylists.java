package imd.ufrn.br.purposesong.use_case;

import java.util.List;

import imd.ufrn.br.purposesong.database.PlaylistRepository;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.entity.User;

public class GetAllUserPlaylists {
    final PlaylistRepository playlistRepository;

    public GetAllUserPlaylists(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> execute(User user) {
        var playlists = this.playlistRepository.findAllPlaylistsOfUser(user);
        return playlists;
    }
}
