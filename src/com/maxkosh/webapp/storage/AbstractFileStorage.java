package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canWrite() || !directory.canRead()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume doGet(File file) {
        for (File fileInDir : Objects.requireNonNull(directory.listFiles())) {
            if (fileInDir.equals(file)) {
                try {
                    return doRead(fileInDir);
                } catch (IOException e) {
                    throw new StorageException("IO error", file.getName(), e);
                }
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        for (File fileInDir : Objects.requireNonNull(directory.listFiles())) {
            if (fileInDir.equals(file)) {
                try {
                    doWrite(resume, fileInDir);
                } catch (IOException e) {
                    throw new StorageException("IO error", file.getName(), e);
                }
            }
        }
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> resumeList = new ArrayList<>();
        for (File fileInDir : Objects.requireNonNull(directory.listFiles())) {
            try {
                resumeList.add(doRead(fileInDir));
            } catch (IOException e) {
                throw new StorageException("IO error", fileInDir.getName(), e);
            }
        }
        return resumeList;
    }

    @Override
    public void clear() {
        for (File file : (Objects.requireNonNull(directory.listFiles()))) {
            file.delete();
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }
}
