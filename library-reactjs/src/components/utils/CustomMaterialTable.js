import React from 'react';
import MaterialTable from 'material-table';
export default function CustomMaterialTable(props) {
	const { columns, data, title, addRow, updateRow, deleteRow } = props;
	return (
		<MaterialTable
			title={title}
			columns={columns}
			data={data}
			editable={{
				onRowAdd: addRow && (
					(newData) =>
						new Promise(async (resolve, reject) => {
							try {
								await addRow(newData);
								resolve();
							} catch (error) {
								reject(error);
							}
						})
				),
				onRowUpdate: (newData, oldData) =>
				new Promise(async(resolve,reject)=>{
					try {
						await updateRow(newData);
						resolve();
					} catch (error) {
						reject(error);
					}
				}),
				onRowDelete: (oldData) => 
				new Promise(async(resolve,reject)=>{
					try {
						await deleteRow(oldData);
						resolve()
					} catch (error) {
						console.log(error)
						reject(error)
					}
				})
			}}
		/>
	);
}
