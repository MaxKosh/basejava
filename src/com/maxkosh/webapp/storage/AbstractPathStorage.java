package com.maxkosh.webapp.storage;

import com.maxkosh.webapp.exception.StorageException;
import com.maxkosh.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writeable");
        }
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    @Override
    protected Path getSearchKey(String uuid) {
        //Files.
        return null;//new Path(directory, uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        /*try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", Path.getName(), e);
        }*/
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        /*try {
            doWrite(resume, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", Path.getName(), e);
        }*/
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Path create error", null, e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> getList() {
        /*List<Resume> resumeList = new ArrayList<>();
        for (Path Path : getListPaths()) {
            resumeList.add(doGet(Path));
        }*/
        return null;//resumeList;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path clear error", null, e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Path size error", null, e);
        }
    }

    private Path[] getListPaths() {
        /*Path[] Paths = directory.listPaths();
        if (Paths != null) {
            return Paths;
        } else {
            throw new StorageException("List get error", directory.getAbsolutePath());
        }*/
        return new Path[0];
    }
}
