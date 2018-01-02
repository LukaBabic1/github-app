package com.undabot.babic.app.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.utils.ui.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class CodeRepositoriesAdapter extends RecyclerView.Adapter<CodeRepositoriesAdapter.CodeRepositoryViewHolder> {

    public interface CodeRepositoriesAdapterListener {

        void onRepositoryClicked(int id);

        void onUserAvatarClicked(int id);
    }

    private final LayoutInflater inflater;
    private final ImageLoader imageLoader;

    private final List<CodeRepositoryViewModel> viewModels = new ArrayList<>();

    private Optional<CodeRepositoriesAdapterListener> listenerOptional = Optional.empty();

    public CodeRepositoriesAdapter(final LayoutInflater inflater, final ImageLoader imageLoader) {
        this.inflater = inflater;
        this.imageLoader = imageLoader;
    }

    @Override
    public CodeRepositoriesAdapter.CodeRepositoryViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new CodeRepositoryViewHolder(inflater.inflate(R.layout.adapter_code_repository_row_item, parent, false), imageLoader);
    }

    @Override
    public void onBindViewHolder(final CodeRepositoriesAdapter.CodeRepositoryViewHolder holder, final int position) {
        holder.setListener(listenerOptional);
        holder.populate(viewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public void setListener(final CodeRepositoriesAdapterListener listener) {
        this.listenerOptional = Optional.ofNullable(listener);
        notifyDataSetChanged();
    }

    public void setItems(final List<CodeRepositoryViewModel> viewModels) {
        this.viewModels.clear();
        this.viewModels.addAll(viewModels);
        notifyDataSetChanged();
    }

    static final class CodeRepositoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_code_repository_row_item_root_view)
        ViewGroup rootView;

        @BindView(R.id.adapter_code_repository_row_item_repository_name)
        TextView repositoryName;

        @BindView(R.id.adapter_code_repository_row_item_owner_avatar_image)
        ImageView authorAvatarImage;

        @BindView(R.id.adapter_code_repository_row_item_author_name)
        TextView authorName;

        @BindView(R.id.adapter_code_repository_row_item_stars_count)
        TextView starsCount;

        @BindView(R.id.adapter_code_repository_row_item_followers_count)
        TextView followersCount;

        @BindView(R.id.adapter_code_repository_row_item_forks_count)
        TextView forksCount;

        @BindView(R.id.adapter_code_repository_row_item_issues_count)
        TextView issuesCount;

        @BindString(R.string.repository_adapter_author_name_template)
        String authorNameTemplate;

        @BindString(R.string.repository_adapter_stars_count)
        String starsCountTemplate;

        @BindString(R.string.repository_adapter_watchers_count)
        String watchersCountTemplate;

        @BindString(R.string.repository_adapter_forks_count_template)
        String forksCountTemplate;

        @BindString(R.string.repository_adapter_open_issues_count_template)
        String openIssuesCountTemplate;

        @BindString(R.string.repository_adapter_score_template)
        String scoreTemplate;

        private final ImageLoader imageLoader;

        private Optional<CodeRepositoryViewModel> viewModel = Optional.empty();
        private Optional<CodeRepositoriesAdapterListener> listenerOptional = Optional.empty();

        CodeRepositoryViewHolder(final View rootView, final ImageLoader imageLoader) {
            super(rootView);
            this.imageLoader = imageLoader;

            bindViews(rootView);
        }

        void setListener(final Optional<CodeRepositoriesAdapterListener> listener) {
            this.listenerOptional = listener;
        }

        void populate(final CodeRepositoryViewModel viewModel) {
            this.viewModel = Optional.of(viewModel);

            repositoryName.setText(viewModel.name);
            authorName.setText(String.format(authorNameTemplate, viewModel.repositoryOwnerViewModel.name));
            starsCount.setText(String.format(starsCountTemplate, viewModel.stargazersCount));
            followersCount.setText(String.format(watchersCountTemplate, viewModel.watchersCount));
            forksCount.setText(String.format(forksCountTemplate, viewModel.forksCount));
            issuesCount.setText(String.format(openIssuesCountTemplate, viewModel.openIssuesCount));

            imageLoader.loadImage(viewModel.repositoryOwnerViewModel.avatarUrl, authorAvatarImage);
        }

        private void bindViews(final View rootView) {
            ButterKnife.bind(this, rootView);
        }

        @OnClick(R.id.adapter_code_repository_row_item_root_view)
        void onRootViewClicked() {
            viewModel.ifPresent(viewModel -> listenerOptional.ifPresent(listener -> listener.onRepositoryClicked(viewModel.id)));
        }

        @OnClick(R.id.adapter_code_repository_row_item_owner_avatar_image)
        void onUserAvatarClicked() {
            viewModel.ifPresent(viewModel -> listenerOptional.ifPresent(listener -> listener.onUserAvatarClicked(viewModel.repositoryOwnerViewModel.id)));
        }
    }
}
