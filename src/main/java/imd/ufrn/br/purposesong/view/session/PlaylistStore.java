package imd.ufrn.br.purposesong.view.session;

import java.util.ArrayList;
import java.util.List;

import imd.ufrn.br.purposesong.database.RepositoryFactory;
import imd.ufrn.br.purposesong.entity.Playlist;
import imd.ufrn.br.purposesong.use_case.AddPlaylist;
import imd.ufrn.br.purposesong.use_case.GetAllUserPlaylists;
import imd.ufrn.br.purposesong.utils.UserAlerts;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class PlaylistStore {
    private UserStore userStore = UserStore.getInstance();
    public ListProperty<Playlist> playlists = new SimpleListProperty<Playlist>(FXCollections.observableArrayList());

    // !Gerencia as properties na memória
    public void setPlaylist(List<Playlist> playlist) {
        this.playlists.setAll(playlist);
    }

    public void resetStore() {
        PlaylistStore.instance = new PlaylistStore();
        playlists.setAll(new ArrayList<>());
    }

    // Scan e busca de musicas
    public void fetchPlaylistListOfCurrentUser() {
        var repoPlaylists = RepositoryFactory.getPlaylistRepository();
        var user = this.userStore.getUser().get();

        List<Playlist> playlistsOfUser = new GetAllUserPlaylists(repoPlaylists).execute(user);
        if (!playlistsOfUser.isEmpty())
            this.setPlaylist(playlistsOfUser);
    }

    // Salvar nos database
    public Playlist savePlaylistInDB(Playlist playlist) {
        if (UserAlerts.alertNewPlaylistConfirmation(playlist)) {
            var repo = RepositoryFactory.getPlaylistRepository();
            new AddPlaylist(repo).execute(playlist);
            return playlist;
        } else
            return null;
    }

    // Singleton ---------------------------
    private static PlaylistStore instance = new PlaylistStore();

    private PlaylistStore() {
    }

    public static PlaylistStore getInstance() {
        return PlaylistStore.instance;
    }

}
