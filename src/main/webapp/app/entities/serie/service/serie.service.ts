import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISerie, getSerieIdentifier } from '../serie.model';

export type EntityResponseType = HttpResponse<ISerie>;
export type EntityArrayResponseType = HttpResponse<ISerie[]>;

@Injectable({ providedIn: 'root' })
export class SerieService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/series');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(serie: ISerie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(serie);
    return this.http
      .post<ISerie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(serie: ISerie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(serie);
    return this.http
      .put<ISerie>(`${this.resourceUrl}/${getSerieIdentifier(serie) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(serie: ISerie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(serie);
    return this.http
      .patch<ISerie>(`${this.resourceUrl}/${getSerieIdentifier(serie) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISerie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISerie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSerieToCollectionIfMissing(serieCollection: ISerie[], ...seriesToCheck: (ISerie | null | undefined)[]): ISerie[] {
    const series: ISerie[] = seriesToCheck.filter(isPresent);
    if (series.length > 0) {
      const serieCollectionIdentifiers = serieCollection.map(serieItem => getSerieIdentifier(serieItem)!);
      const seriesToAdd = series.filter(serieItem => {
        const serieIdentifier = getSerieIdentifier(serieItem);
        if (serieIdentifier == null || serieCollectionIdentifiers.includes(serieIdentifier)) {
          return false;
        }
        serieCollectionIdentifiers.push(serieIdentifier);
        return true;
      });
      return [...seriesToAdd, ...serieCollection];
    }
    return serieCollection;
  }

  protected convertDateFromClient(serie: ISerie): ISerie {
    return Object.assign({}, serie, {
      dateHeureAjout: serie.dateHeureAjout?.isValid() ? serie.dateHeureAjout.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateHeureAjout = res.body.dateHeureAjout ? dayjs(res.body.dateHeureAjout) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((serie: ISerie) => {
        serie.dateHeureAjout = serie.dateHeureAjout ? dayjs(serie.dateHeureAjout) : undefined;
      });
    }
    return res;
  }
}
