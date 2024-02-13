import {createAction, props} from "@ngrx/store";

export const login = createAction('[Authorization] Login', props<{login: string, password: string}>());
export const logout = createAction('[Authorization] Logout');
