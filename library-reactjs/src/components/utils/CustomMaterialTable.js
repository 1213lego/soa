import React from 'react';
import MaterialTable from 'material-table';
export default function CustomMaterialTable(props) {
	const { columns, data, title, addRow, updateRow, deleteRow } = props;
	return (
		<div style={{maxWidth: '100%'}}>
			<MaterialTable title={title} columns={columns} data={data} />
		</div>
	);
}
