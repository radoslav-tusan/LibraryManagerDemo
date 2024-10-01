package com.rtusan.librarymanagerdemo.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceBase<D extends DtoBase, I> {

  D create(D dto);

  D update(I id, D update);

  Page<D> getAll(Pageable pageable);

  /**
   * Returns the D based on its ID, throws an exception if not found.
   *
   * @param id the ID
   * @return the D with given ID
   * @throws ServiceException if not found.
   */
  D getById(I id) throws ServiceException;

  /**
   * Returns the D based on its ID or null if not found.
   *
   * @param id the ID
   * @return the D with given ID
   */
  D findById(I id);

  void delete(I id);
}
